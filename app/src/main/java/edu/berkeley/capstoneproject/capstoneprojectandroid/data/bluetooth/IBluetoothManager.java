package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Alex on 13/01/2018.
 */

public interface IBluetoothManager {
    Observable<Rx2BleDevice> doScan();

    Observable<Rx2BleDevice> doGetPairedDevices();

    Completable doConnect();
    Completable doDisconnect();
    Completable doValidate();

    Rx2BleDevice getDevice();
    void setDevice(Rx2BleDevice device);
}
