package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothDevice;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface BluetoothListContract {

    interface View extends IBaseView {
        void addScannedDevice(BluetoothDevice device);
        void addPairedDevice(BluetoothDevice device);
        void cleanScannedDevices();
        void cleanPairedDevices();
        void showScanningProgress();
        void hideScanningProgress();
        void showError(String message);

        void startExercisesActivity(BluetoothDevice device);
    }

    interface Interactor extends IBaseInteractor {
        Observable<BluetoothDevice> doDiscovery();
        Observable<BluetoothDevice> doLoadPairedDevices();
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onLoadPairedDevices();
        void onStartScanning();
        void onStopScanning();
        void onDeviceClick(BluetoothDevice device);
    }
}
