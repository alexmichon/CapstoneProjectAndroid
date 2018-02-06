package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.MeasurementFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.bytes.BytesDecoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IRxWebSocket;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 21/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class TrainingManagerTest {

    private TrainingManager mTrainingManager;

    @Mock
    private IApiHelper mApiHelper;

    @Mock
    private edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService mNetExerciseService;



    @Mock
    private IBluetoothHelper mBluetoothHelper;

    @Mock
    private edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService mBtExerciseService;

    @Mock
    private IMeasurementService mMeasurementService;



    @Mock
    private BytesDecoder mBytesDecoder;



    @Before
    public void before() {
        doReturn(mNetExerciseService).when(mApiHelper).getExerciseService();

        doReturn(mBtExerciseService).when(mBluetoothHelper).getExerciseService();
        doReturn(mMeasurementService).when(mBluetoothHelper).getMeasurementService();

        mTrainingManager = new TrainingManager(mApiHelper, mBluetoothHelper, mBytesDecoder);
    }


    @Test
    public void doStartSensorsShouldCallBluetoothHelper() {
        // given
        doReturn(Single.never()).when(mBtExerciseService).doStartExercise();

        // when
        mTrainingManager.doStartSensors();

        // then
        verify(mBtExerciseService).doStartExercise();
    }

    @Test
    public void doStartSensorsShouldUpdateMeasurementService() {
        // given
        Observable<byte[]> encoderObservable = Observable.never();
        Observable<byte[]> imuObservable = Observable.never();
        Map<String, Observable<byte[]>> map = new HashMap<>();
        map.put(IExerciseService.ENCODER_OBSERVABLE, encoderObservable);
        map.put(IExerciseService.IMU_OBSERVABLE, imuObservable);
        doReturn(Single.just(map)).when(mBtExerciseService).doStartExercise();

        // when
        mTrainingManager.doStartSensors().test();

        // then
        verify(mMeasurementService).setEncoderObservable(encoderObservable);
        verify(mMeasurementService).setImuObservable(imuObservable);
    }

    @Test
    public void doStopSensorsShouldDisposeSubscription() {
        // given
        Observable<byte[]> encoderObservable = Observable.never();
        Observable<byte[]> imuObservable = Observable.never();
        doReturn(encoderObservable).when(mMeasurementService).getEncoderObservable();
        doReturn(imuObservable).when(mMeasurementService).getImuObservable();

        mTrainingManager.doListen().test();

        // when
        mTrainingManager.doStopSensors().test();

        // then
        assertFalse(mTrainingManager.isListening());
    }

    @Test
    public void doListenShouldMergeImuAndEncoderObservable() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);

        Observable<byte[]> encoderObservable = Observable.never();
        Observable<byte[]> imuObservable = Observable.never();

        doReturn(encoderObservable).when(mMeasurementService).getEncoderObservable();
        doReturn(imuObservable).when(mMeasurementService).getImuObservable();

        // when
        mTrainingManager.doListen().test();

        // then
        verify(mMeasurementService).getImuObservable();
        verify(mMeasurementService).getEncoderObservable();
        assertTrue(mTrainingManager.isListening());
    }

    @Test
    public void doListenShouldDecodeImuAndEncoderObservable() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);

        byte[] encoderBytes = new byte[] {(byte) 0xAA};
        byte[] imuBytes = new byte[] {(byte) 0xBB};
        Observable<byte[]> encoderObservable = Observable.just(encoderBytes);
        Observable<byte[]> imuObservable = Observable.just(imuBytes);

        doReturn(encoderObservable).when(mMeasurementService).getEncoderObservable();
        doReturn(imuObservable).when(mMeasurementService).getImuObservable();

        doReturn(new ArrayList<Measurement>()).when(mBytesDecoder).decodeEncoder(exercise, encoderBytes);
        doReturn(new ArrayList<Measurement>()).when(mBytesDecoder).decodeImu(exercise, imuBytes);

        // when
        mTrainingManager.doListen().test();

        // then
        verify(mBytesDecoder).decodeEncoder(exercise, encoderBytes);
        verify(mBytesDecoder).decodeImu(exercise, imuBytes);
    }

    @Test
    public void doStartStreamingShouldConnectToSocket() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);

        IRxWebSocket socket = Mockito.mock(IRxWebSocket.class);
        doReturn(socket).when(mNetExerciseService).doStartStreaming(exercise);

        // when
        mTrainingManager.doStartStreaming();

        // then
        verify(mNetExerciseService).doStartStreaming(exercise);
        verify(socket).connect();
    }

    @Test
    public void doSendMeasurementShouldSendMeasurementToSocket() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);

        Measurement measurement = MeasurementFactory.builder()
                .build();

        IRxWebSocket socket = Mockito.mock(IRxWebSocket.class);
        doReturn(socket).when(mNetExerciseService).doStartStreaming(exercise);
        mTrainingManager.doStartStreaming();

        // when
        mTrainingManager.doSendMeasurement(measurement);

        // then
        verify(mNetExerciseService).doSendMeasurement(socket, measurement);
    }

    @Test
    public void doStopStreamingShouldDisconnectFromSocket() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);

        IRxWebSocket socket = Mockito.mock(IRxWebSocket.class);
        doReturn(socket).when(mNetExerciseService).doStartStreaming(exercise);
        mTrainingManager.doStartStreaming();

        // when
        mTrainingManager.doStopStreaming();

        // then
        verify(socket).disconnect();
    }
    
    
    @Test
    public void doGetExerciseGoalShouldCallApiHelper() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);
        doReturn(Single.never()).when(mNetExerciseService).doGetExerciseGoal(exercise);
        
        // when
        mTrainingManager.doGetExerciseGoal();
        
        // then
        verify(mNetExerciseService).doGetExerciseGoal(exercise);
    }

    @Test
    public void doGetExerciseResultShouldCallApiHelper() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);
        doReturn(Single.never()).when(mNetExerciseService).doGetExerciseResult(exercise);

        // when
        mTrainingManager.doGetExerciseResult();

        // then
        verify(mNetExerciseService).doGetExerciseResult(exercise);
    }



    @Test
    public void doStopExerciseShouldCallApiHelper() {
        // given
        Exercise exercise = ExerciseFactory.create();
        mTrainingManager.setCurrentExercise(exercise);
        doReturn(Completable.never()).when(mNetExerciseService).doStopExercise(exercise);

        // when
        mTrainingManager.doStopExercise();

        // then
        verify(mNetExerciseService).doStopExercise(exercise);
    }
}
