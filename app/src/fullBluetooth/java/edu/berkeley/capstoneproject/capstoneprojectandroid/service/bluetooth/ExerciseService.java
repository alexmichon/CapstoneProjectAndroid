package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import com.polidea.rxandroidble.NotificationSetupMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.BluetoothConstants;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

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
    public void setConnection(Rx2BleConnection connection) {
        mConnection = connection;
    }

    @Override
    public Observable<Map<String, Observable<byte[]>>> doStartExercise() {
        // TODO
        Observable<Observable<byte[]>> encoderObservableObservable = mConnection.setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_ENCODER, NotificationSetupMode.DEFAULT);
        //return mConnection.setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_IMU, NotificationSetupMode.DEFAULT);
        //Observable<Observable<byte[]>> encoderObservableObservable  = Observable.just(Observable.<byte[]>never());
        Observable<Observable<byte[]>> imuObservableObservable = mConnection.setupNotification(BluetoothConstants.UUID_CHARACTERISTIC_IMU, NotificationSetupMode.DEFAULT);
        return Observable.zip(encoderObservableObservable, imuObservableObservable, new BiFunction<Observable<byte[]>, Observable<byte[]>, Map<String, Observable<byte[]>>>() {
            @Override
            public Map<String, Observable<byte[]>> apply(Observable<byte[]> observable, Observable<byte[]> observable2) throws Exception {
                Map<String, Observable<byte[]>> map = new HashMap<String, Observable<byte[]>>();
                map.put(IMU_OBSERVABLE, observable);
                //map.put(ENCODER_OBSERVABLE, encoderObservable);
                return map;
            }

            //return imuObservableObservable.map(new Function<Observable<byte[]>, Map<String, Observable<byte[]>>>() {
            /*@Override
            public Map<String, Observable<byte[]>> apply(Observable<byte[]> observable) throws Exception {
                Map<String, Observable<byte[]>> map = new HashMap<String, Observable<byte[]>>();
                map.put(IMU_OBSERVABLE, observable);
                //map.put(ENCODER_OBSERVABLE, encoderObservable);
                return map;
            }*/
        });
    }
}
