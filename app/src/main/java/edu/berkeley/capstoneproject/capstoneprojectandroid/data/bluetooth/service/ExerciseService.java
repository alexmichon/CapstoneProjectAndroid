package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service;

import com.polidea.rxandroidble.NotificationSetupMode;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by Alex on 18/11/2017.
 */

public class ExerciseService implements IExerciseService {

    private final IBluetoothHelper mBluetoothHelper;

    private Disposable mEncoderNotificationObservable;
    private Disposable mImuNotificationObservable;

    public ExerciseService(IBluetoothHelper helper) {
        mBluetoothHelper = helper;
    }

    @Override
    public Observable<ISensorService> startExercise() {
        // TODO
        Observable<Observable<byte[]>> encoderObservableObservable = mBluetoothHelper.setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_ENCODER, NotificationSetupMode.DEFAULT);
        Observable<Observable<byte[]>> imuObservableObservable = mBluetoothHelper.setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_IMU, NotificationSetupMode.DEFAULT);

        return Observable.zip(encoderObservableObservable, imuObservableObservable, new BiFunction<Observable<byte[]>, Observable<byte[]>, ISensorService>() {
            @Override
            public ISensorService apply(@NonNull Observable<byte[]> encoderObservable, @NonNull Observable<byte[]> imuObservable) throws Exception {
                ISensorService sensorService = mBluetoothHelper.getSensorService();
                sensorService.setEncoderObservable(encoderObservable);
                sensorService.setImuObservable(imuObservable);
                return sensorService;
            }
        });
    }
}
