package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 08/11/2017.
 */

public abstract class BluetoothLeAdapter {

    private static final String TAG = BluetoothLeAdapter.class.getSimpleName();


    protected final BluetoothAdapter mAdapter;
    protected boolean mScanning = false;
    protected BluetoothScanListener mScanListener;

    public BluetoothLeAdapter() {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public BluetoothLeAdapter(BluetoothAdapter adapter) {
        mAdapter = adapter;
    }

    public abstract void startDiscovery();
    public abstract void cancelDiscovery();

    public List<BluetoothDevice> getPairedDevices() {
        return new ArrayList<>(mAdapter.getBondedDevices());
    }

    public void setScanListener(BluetoothScanListener listener) {
        mScanListener = listener;
    }

    public interface BluetoothScanListener {
        void onNewDevice(BluetoothDevice device);
        void onComplete();
        void onError(Throwable t);
    }
}
