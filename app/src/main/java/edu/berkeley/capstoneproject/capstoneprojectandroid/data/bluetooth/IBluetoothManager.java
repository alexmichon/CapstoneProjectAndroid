package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Alex on 13/01/2018.
 */

public interface IBluetoothManager {
    Observable<Rx2BleDevice> doStartScanning();

    Completable doStopScanning();

    Observable<Rx2BleDevice> doGetPairedDevices();

    Completable doConnect(Rx2BleDevice device);
    Completable doDisconnect();
    Completable doValidate();

    Rx2BleDevice getDevice();
    Rx2BleConnection getConnection();
    boolean getBluetoothStatus();

    boolean isConnected();
}
