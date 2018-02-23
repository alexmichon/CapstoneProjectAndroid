package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import com.polidea.rxandroidble.NotificationSetupMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by Alex on 18/11/2017.
 */

public class ExerciseService extends BaseService implements IExerciseService {

    public static final String ENCODER_OBSERVABLE = "EncoderObservable";
    public static final String IMU_OBSERVABLE = "ImuObservable";
    private Rx2BleConnection mConnection;

    private Disposable mEncoderNotificationObservable;
    private Disposable mImuNotificationObservable;

    @Inject
    public ExerciseService() {
        // Empty constructor
    }

    @Override
    public Single<Map<String, Observable<byte[]>>> doStartExercise() {
        // TODO
        Observable<Observable<byte[]>> encoderObservableObservable = mConnection.setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_ENCODER, NotificationSetupMode.DEFAULT);
        Observable<Observable<byte[]>> imuObservableObservable = mConnection.setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_IMU, NotificationSetupMode.DEFAULT);
        return Observable.zip(encoderObservableObservable, imuObservableObservable, new BiFunction<Observable<byte[]>, Observable<byte[]>, Map<String, Observable<byte[]>>>() {
            @Override
            public Map<String, Observable<byte[]>> apply(@NonNull Observable<byte[]> encoderObservable, @NonNull Observable<byte[]> imuObservable) throws Exception {
                Map<String, Observable<byte[]>> map = new HashMap<String, Observable<byte[]>>();
                map.put(IMU_OBSERVABLE, imuObservable);
                map.put(ENCODER_OBSERVABLE, encoderObservable);
                return map;
            }
        }).singleOrError();
    }
}
