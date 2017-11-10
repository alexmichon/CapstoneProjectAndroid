package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

/**
 * Created by Alex on 08/11/2017.
 */

@TargetApi(18)
public class BluetoothLe18Adapter extends BluetoothLeAdapter {

    private static final String TAG = BluetoothLe18Adapter.class.getSimpleName();

    public BluetoothLe18Adapter() {
    }

    @Override
    public void startDiscovery() {
        if (mScanning) {
            cancelDiscovery();
        }
        mScanning = true;
        mAdapter.startLeScan(mScanCallback);
    }

    public void cancelDiscovery() {
        if (mScanning) {
            mAdapter.stopLeScan(mScanCallback);
        }
        mScanning = false;
    }

    private BluetoothAdapter.LeScanCallback mScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
            Log.d(TAG, "New LE device scanned");
            if (mScanListener != null) {
                mScanListener.onNewDevice(bluetoothDevice);
            }
        }
    };
}
