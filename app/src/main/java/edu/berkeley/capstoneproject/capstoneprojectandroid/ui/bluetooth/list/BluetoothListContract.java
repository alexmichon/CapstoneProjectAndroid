package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface BluetoothListContract {

    interface View extends IBaseView {
        void promptBluetooth();

        void addScannedDevice(Rx2BleDevice device);
        void addPairedDevice(Rx2BleDevice device);
        void cleanScannedDevices();
        void cleanPairedDevices();
        void showScanningProgress();
        void hideScanningProgress();

        void onDeviceConnecting();
        void onDeviceConnected();
    }

    interface Interactor extends IBaseInteractor {
        Observable<Rx2BleDevice> doStartScanning();
        Completable doStopScanning();

        Observable<Rx2BleDevice> doLoadPairedDevices();

        Completable doConnect(Rx2BleDevice device);
        Completable doDisconnect();
        Completable doValidateDevice();

        boolean getBluetoothState();

        boolean isConnected();
    }

    //@PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onStart();

        void onLoadPairedDevices();
        void startScanning();
        void stopScanning();;

        void onDeviceSelected(Rx2BleDevice device);
    }
}
