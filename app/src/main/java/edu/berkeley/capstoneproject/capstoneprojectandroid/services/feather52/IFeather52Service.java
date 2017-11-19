package edu.berkeley.capstoneproject.capstoneprojectandroid.services.feather52;

import com.polidea.rxandroidble.NotificationSetupMode;
import com.polidea.rxandroidble.RxBleConnection;

import java.util.UUID;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;

/**
 * Created by Alex on 17/11/2017.
 */

@Singleton
public interface IFeather52Service {

    void setDevice(Rx2BleDevice device);

    Rx2BleDevice getDevice();

    Observable<Rx2BleDevice.ConnectionState> connect(boolean autoconnect);

    Rx2BleDevice.ConnectionState getConnectionState();

    Observable<Rx2BleDevice.ConnectionState> observeConnectionStateChange();


    Observable<Boolean> validateDevice();

    Observable<byte[]> readCharacteristic(UUID uuid);

    Observable<byte[]> writeCharacteristic(UUID uuid, byte[] bytes);

    Observable<Rx2BleDevice.ConnectionState> disconnect();

    Observable<Observable<byte[]>> setupNotification(UUID uuid, NotificationSetupMode setupMode);
}
