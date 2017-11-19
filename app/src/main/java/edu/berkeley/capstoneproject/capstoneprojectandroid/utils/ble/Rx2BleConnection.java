package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble;

/**
 * Created by Alex on 17/11/2017.
 */


import com.polidea.rxandroidble.NotificationSetupMode;
import com.polidea.rxandroidble.RxBleConnection;
import com.polidea.rxandroidble.RxBleDeviceServices;

import java.util.UUID;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.convert.RxObservableConverter;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/** Wrapper of the RxBleConnection class using RxJava2 */
public class Rx2BleConnection {

    private static final String TAG = Rx2BleConnection.class.getSimpleName();

    private final RxBleConnection mConnection;

    public Rx2BleConnection(RxBleConnection connection) {
        mConnection = connection;
    }

    public Observable<byte[]> readCharacteristic(UUID uuid) {
        final rx.Observable<byte[]> observable = mConnection.readCharacteristic(uuid);
        return RxObservableConverter.convert(observable);
    }


    public Observable<byte[]> writeCharacteristic(UUID uuid, byte[] bytes) {
        final rx.Observable<byte[]> observable = mConnection.writeCharacteristic(uuid, bytes);
        return RxObservableConverter.convert(observable);
    }

    public Observable<Rx2BleDeviceServices> discoverServices() {
        final rx.Observable<RxBleDeviceServices> observable = mConnection.discoverServices();
        return RxObservableConverter.convert(observable).map(new Function<RxBleDeviceServices, Rx2BleDeviceServices>() {
            @Override
            public Rx2BleDeviceServices apply(@NonNull RxBleDeviceServices services) throws Exception {
                return new Rx2BleDeviceServices(services);
            }
        });
    }

    public Observable<Observable<byte[]>> setupNotification(UUID uuid, NotificationSetupMode setupMode) {
        final rx.Observable<rx.Observable<byte[]>> observable = mConnection.setupNotification(uuid, setupMode);
        return RxObservableConverter.convert(observable).map(new Function<rx.Observable<byte[]>, Observable<byte[]>>() {
            @Override
            public Observable<byte[]> apply(@NonNull rx.Observable<byte[]> observable) throws Exception {
                return RxObservableConverter.convert(observable);
            }
        });
    }
}
