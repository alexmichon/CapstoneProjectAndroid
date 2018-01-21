package edu.berkeley.capstoneproject.capstoneprojectandroid.service;

import com.polidea.rxandroidble.NotificationSetupMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;
import io.reactivex.Observable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 25/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceTest {

    private ExerciseService mExerciseService;

    @Mock
    private Rx2BleConnection mConnection;

    @Before
    public void setup() {
        mExerciseService = new ExerciseService();
        mExerciseService.setConnection(mConnection);
    }

    @Test
    public void startExerciseShouldEnableNotifications() {
        // given
        Observable encoderObservable = Observable.never();
        Observable imuObservable = Observable.never();
        doReturn(encoderObservable).when(mConnection).setupNotification(eq(BluetoothConstants.UUID_CHARACTERISTIC_ENCODER), any(NotificationSetupMode.class));
        doReturn(imuObservable).when(mConnection).setupNotification(eq(BluetoothConstants.UUID_CHARACTERISTIC_IMU), any(NotificationSetupMode.class));

        // when
        mExerciseService.startExercise();

        // then
        verify(mConnection).setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_ENCODER, NotificationSetupMode.DEFAULT);
        verify(mConnection).setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_IMU, NotificationSetupMode.DEFAULT);
    }

    @Test
    public void startExerciseShouldZipObservables() {
        // given
        Observable encoderObservable = Observable.never();
        Observable encoderObservableObservable = Observable.just(encoderObservable);
        Observable imuObservable = Observable.never();
        Observable imuObservableObservable = Observable.just(imuObservable);
        doReturn(encoderObservableObservable).when(mConnection).setupNotification(eq(BluetoothConstants.UUID_CHARACTERISTIC_ENCODER), any(NotificationSetupMode.class));
        doReturn(imuObservableObservable).when(mConnection).setupNotification(eq(BluetoothConstants.UUID_CHARACTERISTIC_IMU), any(NotificationSetupMode.class));

        // when
        Observable<Map<String, Observable<byte[]>>> observable = mExerciseService.startExercise();
        Map<String, Observable<byte[]>> map = observable.blockingFirst();

        // then
        assertThat(map, hasValue(encoderObservable));
        assertThat(map, hasValue(imuObservable));
    }
}
