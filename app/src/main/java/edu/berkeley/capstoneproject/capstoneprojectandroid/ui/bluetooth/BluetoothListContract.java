package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothDevice;

import java.util.List;

/**
 * Created by Alex on 07/11/2017.
 */

public interface BluetoothListContract {

    interface View {
        void addScannedDevice(BluetoothDevice device);
        void addPairedDevice(BluetoothDevice device);
        void cleanScannedDevices();
        void cleanPairedDevices();
        void showScanningProgress();
        void hideScanningProgress();
        void showError(String message);
    }

    interface Presenter {
        void startDiscovery();
        void cancelDiscovery();
        void getPairedDevices();
    }
}
