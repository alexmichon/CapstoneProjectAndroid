package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import com.polidea.rxandroidble.NotificationSetupMode;

import java.util.UUID;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.ISensorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public interface IBluetoothHelper {

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

    IExerciseService getExerciseService();

    ISensorService getSensorService();
}
