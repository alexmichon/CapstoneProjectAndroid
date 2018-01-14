package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 13/01/2018.
 */

@Singleton
public class BluetoothManager implements IBluetoothManager {

    private final IBluetoothHelper mBluetoothHelper;

    private Rx2BleDevice mDevice;

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

    @Override
    public Completable doConnect() {
        return mBluetoothHelper.getConnectionService().connect(mDevice, false)
                .flatMapCompletable(new Function<Rx2BleConnection, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final Rx2BleConnection rx2BleConnection) throws Exception {
                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                mBluetoothHelper.setConnection(rx2BleConnection);
                                mBluetoothHelper.setDevice(mDevice);
                            }
                        });
                    }
                });
    }

    @Override
    public Completable doDisconnect() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mBluetoothHelper.getConnectionService().disconnect();
            }
        });
    }

    @Override
    public Completable doValidate() {
        return mBluetoothHelper.getConnectionService().validateDevice();
    }

    @Override
    public Rx2BleDevice getDevice() {
        return mDevice;
    }

    @Override
    public void setDevice(Rx2BleDevice device) {
        mDevice = device;
    }
}
