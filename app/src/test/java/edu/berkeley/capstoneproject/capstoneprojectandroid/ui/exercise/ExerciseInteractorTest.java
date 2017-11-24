package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.ISensorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementRequest;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

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
    private edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IExerciseService mApiExerciseService;

    @Mock
    private edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.IExerciseService mBluetoothExerciseService;

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
        ISensorService sensorService = Mockito.mock(ISensorService.class);
        doReturn(Observable.just(sensorService)).when(mBluetoothExerciseService).startExercise();

        // when
        mInteractor.doStartExercise(mExercise).test();

        // then
        verify(mBluetoothExerciseService).startExercise();
    }

    @Test
    public void doStartExerciseShouldReturnOnComplete() {
        // given
        ISensorService sensorService = Mockito.mock(ISensorService.class);
        Observable observable = Observable.just(sensorService);
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
        ISensorService sensorService = Mockito.mock(ISensorService.class);
        Observable<ISensorService> observable = Mockito.mock(Observable.class);

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
        ISensorService sensorService = Mockito.mock(ISensorService.class);
        Observable<ISensorService> observable = Observable.just(sensorService);
        Observable<Measurement> imuObservable = Observable.empty();

        doReturn(observable).when(mBluetoothExerciseService).startExercise();
        Completable completable = mInteractor.doStartExercise(mExercise);
        completable.blockingAwait();

        doReturn(imuObservable).when(sensorService).getImuObservable();

        // when
        mInteractor.doListenImu();

        // then
        verify(sensorService).getImuObservable();
    }

    @Test
    public void doListenEncoderShouldCallSensorService() {
        // given
        ISensorService sensorService = Mockito.mock(ISensorService.class);
        Observable<ISensorService> observable = Observable.just(sensorService);
        Observable<Measurement> encoderObservable = Observable.empty();

        doReturn(observable).when(mBluetoothExerciseService).startExercise();
        Completable completable = mInteractor.doStartExercise(mExercise);
        completable.blockingAwait();

        doReturn(encoderObservable).when(sensorService).getEncoderObservable();

        // when
        mInteractor.doListenEncoder();

        // then
        verify(sensorService).getEncoderObservable();
    }

}
