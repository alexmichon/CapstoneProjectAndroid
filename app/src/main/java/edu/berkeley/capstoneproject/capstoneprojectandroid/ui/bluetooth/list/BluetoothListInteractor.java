package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class BluetoothListInteractor extends BaseInteractor implements BluetoothListContract.Interactor {;

    private final IBluetoothManager mBluetoothManager;

    @Inject
    public BluetoothListInteractor(IBluetoothManager bluetoothManager) {
        mBluetoothManager = bluetoothManager;
    }

    @Override
    public Observable<Rx2BleDevice> doDiscovery() {
        return mBluetoothManager.doScan();
    }

    @Override
    public Observable<Rx2BleDevice> doLoadPairedDevices() {
        return mBluetoothManager.doGetPairedDevices();
    }

    @Override
    public void doSelectDevice(Rx2BleDevice device) {
        mBluetoothManager.setDevice(device);
    }

    @Override
    public Completable doConnect(final Rx2BleDevice device) {
        return mBluetoothManager.doConnect();
    }

    @Override
    public Completable doValidateDevice() {
        return mBluetoothManager.doValidate();
    }

    @Override
    public Completable doDisconnect() {
        return mBluetoothManager.doDisconnect();
    }
}
