package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;

/**
 * Created by Alex on 13/01/2018.
 */

@Singleton
public class BluetoothManager implements IBluetoothManager {

    private final IBluetoothHelper mBluetoothHelper;

    @Inject
    public BluetoothManager(IBluetoothHelper bluetoothHelper) {
        mBluetoothHelper = bluetoothHelper;
    }

    @Override
    public Observable<Rx2BleDevice> doScan() {
        return mBluetoothHelper.getDeviceService().getScannedDevices();
    }

    @Override
    public Observable<Rx2BleDevice> doGetPairedDevices() {
        return mBluetoothHelper.getDeviceService().getPairedDevices();
    }
}
