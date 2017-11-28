package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementRequest;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 23/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseInteractorTest {

    private ExerciseInteractor mInteractor;

    @Mock
    private Exercise mExercise;

    @Mock
    private ExerciseType mExerciseType;

    @Mock
    private DataManager mDataManager;

    @Mock
    private IApiHelper mApiHelper;

    @Mock
    private IBluetoothHelper mBluetoothHelper;

    @Mock
    private edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService mApiExerciseService;

    @Mock
    private IExerciseService mBluetoothExerciseService;

    @Before
    public void setup() {
        doReturn(mApiHelper).when(mDataManager).getApiHelper();
        doReturn(mApiExerciseService).when(mApiHelper).getExerciseService();

        doReturn(mBluetoothHelper).when(mDataManager).getBluetoothHelper();
        doReturn(mBluetoothExerciseService).when(mBluetoothHelper).getExerciseService();

        mExercise = Mockito.mock(Exercise.class);
        mExerciseType = Mockito.mock(ExerciseType.class);

        mInteractor = new ExerciseInteractor(mDataManager);
    }

    @Test
    public void doCreateExerciseShouldCallApi() {
        // given
        ExerciseRequest request = new ExerciseRequest(mExerciseType);
        doReturn(Single.never()).when(mApiExerciseService).doCreateExercise(request);

        // when
        mInteractor.doCreateExercise(mExerciseType);

        // then
        verify(mApiExerciseService).doCreateExercise(request);
    }

    @Test
    public void doCreateExerciseShouldReturnCorrectExercise() {
        // given
        ExerciseResponse response = Mockito.mock(ExerciseResponse.class);
        doReturn(mExercise).when(response).getExercise(mExerciseType);
        doReturn(Single.just(response)).when(mApiExerciseService).doCreateExercise(any(ExerciseRequest.class));

        // when
        Exercise exercise = mInteractor.doCreateExercise(mExerciseType).blockingGet();

        // then
        assertEquals(exercise, mExercise);
    }




    @Test
    public void doStartExerciseShouldCallBluetooth() {
        // given
        IMeasurementService sensorService = Mockito.mock(IMeasurementService.class);
        doReturn(Observable.just(sensorService)).when(mBluetoothExerciseService).startExercise();

        // when
        mInteractor.doStartExercise(mExercise).test();

        // then
        verify(mBluetoothExerciseService).startExercise();
    }

    @Test
    public void doStartExerciseShouldReturnOnComplete() {
        // given
        IMeasurementService sensorService = Mockito.mock(IMeasurementService.class);
        doReturn(sensorService).when(mBluetoothHelper).getMeasurementService();
        Observable observable = Observable.just(new HashMap<String, Observable<byte[]>>());
        doReturn(observable).when(mBluetoothExerciseService).startExercise();

        // when
        Completable completable = mInteractor.doStartExercise(mExercise);
        TestObserver testObserver = completable.test();

        // then
        observable.test().assertSubscribed();
        testObserver.assertComplete();
    }

    @Test
    public void doStartExerciseShouldForwardErrorOnFailure() {
        // given
        Error error = new Error();
        Observable observable = Observable.error(error);
        doReturn(observable).when(mBluetoothExerciseService).startExercise();

        // when
        Completable completable = mInteractor.doStartExercise(mExercise);
        TestObserver testObserver = completable.test();

        // then
        testObserver.assertError(error);
    }

    @Test
    public void doStopExerciseShouldDisposeSubscription() {
        // given
        IMeasurementService sensorService = Mockito.mock(IMeasurementService.class);
        Observable<IMeasurementService> observable = Mockito.mock(Observable.class);

        doReturn(observable).when(mBluetoothExerciseService).startExercise();
        mInteractor.doStartExercise(mExercise);

        // when
        mInteractor.doStopExercise();

        // then
        // TODO
    }

    @Test
    public void doSaveMeasurementShouldCallApi() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);

        Metric metric = Mockito.mock(Metric.class);
        when(measurement.getMetric()).thenReturn(metric);
        when(metric.getId()).thenReturn(0);

        when(measurement.getExercise()).thenReturn(mExercise);

        MeasurementRequest request = new MeasurementRequest(measurement);

        doReturn(Single.just(measurement)).when(mApiExerciseService).doCreateMeasurement(request);

        // when
        mInteractor.doSaveMeasurement(mExercise, measurement);

        // then
        verify(mApiExerciseService).doCreateMeasurement(request);
    }

    @Test
    public void doListenImuShouldCallSensorService() {
        // given
        IMeasurementService sensorService = Mockito.mock(IMeasurementService.class);
        doReturn(sensorService).when(mBluetoothHelper).getMeasurementService();

        Observable<byte[]> imuObservable = Observable.never();
        doReturn(imuObservable).when(sensorService).getImuObservable();

        // when
        mInteractor.doListenImu();

        // then
        verify(sensorService).getImuObservable();
    }

    @Test
    public void doListenEncoderShouldCallSensorService() {
        // given
        IMeasurementService sensorService = Mockito.mock(IMeasurementService.class);
        doReturn(sensorService).when(mBluetoothHelper).getMeasurementService();

        Observable<byte[]> encoderObservable = Observable.never();
        doReturn(encoderObservable).when(sensorService).getEncoderObservable();

        // when
        mInteractor.doListenEncoder();

        // then
        verify(sensorService).getEncoderObservable();
    }

}
