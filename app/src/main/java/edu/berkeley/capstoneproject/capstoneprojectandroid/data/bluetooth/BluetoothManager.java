package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 13/01/2018.
 */

@Singleton
public class BluetoothManager implements IBluetoothManager {

    private final IBluetoothHelper mBluetoothHelper;

    private Rx2BleConnection mConnection;
    private Rx2BleDevice mDevice;

    private Disposable mScanningDisposable;

    @Inject
    public BluetoothManager(IBluetoothHelper bluetoothHelper) {
        mBluetoothHelper = bluetoothHelper;
    }

    @Override
    public Observable<Rx2BleDevice> doStartScanning() {
        return mBluetoothHelper.getDeviceService().getScannedDevices()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mScanningDisposable = disposable;
                    }
                });
    }

    @Override
    public Completable doStopScanning() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if (mScanningDisposable != null) {
                    mScanningDisposable.dispose();
                }
            }
        });
    }

    @Override
    public Observable<Rx2BleDevice> doGetPairedDevices() {
        return mBluetoothHelper.getDeviceService().getPairedDevices();
    }

    @Override
    public Completable doConnect(final Rx2BleDevice device) {
        return mBluetoothHelper.getConnectionService().connect(device, false)
                .flatMapCompletable(new Function<Rx2BleConnection, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final Rx2BleConnection rx2BleConnection) throws Exception {
                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                mDevice = device;
                                mConnection = rx2BleConnection;
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
        return mBluetoothHelper.getConnectionService().validateDevice(mConnection);
    }

    @Override
    public Rx2BleDevice getDevice() {
        return mDevice;
    }

    @Override
    public boolean getBluetoothStatus() {
        return mBluetoothHelper.getBluetoothStatus();
    }

    @Override
    public boolean isConnected() {
        return (mConnection != null && mDevice.getConnectionState() == Rx2BleDevice.ConnectionState.CONNECTED);
    }
}
