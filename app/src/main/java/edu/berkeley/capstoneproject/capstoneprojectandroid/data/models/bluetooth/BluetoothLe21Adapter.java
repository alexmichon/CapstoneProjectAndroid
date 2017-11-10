package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 08/11/2017.
 */

@TargetApi(21)
public class BluetoothLe21Adapter extends BluetoothLeAdapter {

    private static final String TAG = BluetoothLe21Adapter.class.getSimpleName();

    private BluetoothLeScanner mScanner;

    private ScanSettings mSettings;
    private List<ScanFilter> mFilters;

    public BluetoothLe21Adapter() {
        mScanner = mAdapter.getBluetoothLeScanner();
        mSettings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build();
        mFilters = new ArrayList<>();
    }

    @Override
    public void startDiscovery() {
        Log.d(TAG, "Start scanning");
        if (mScanning) {
            cancelDiscovery();
        }
        mScanning = true;
        mScanner.startScan(mFilters, mSettings, mScanCallback);
    }

    @Override
    public void cancelDiscovery() {
        mScanner.stopScan(mScanCallback);
    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            Log.i(TAG, "New LE device scanned");
            if (mScanListener != null) {
                mScanListener.onNewDevice(result.getDevice());
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            Log.d(TAG, "Scan finished");
            if (mScanListener != null) {
                mScanListener.onComplete();
            }
            mScanning = false;
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.e(TAG, "Scan failed");
            if (mScanListener != null) {
                mScanListener.onError(new Throwable("Scan failed"));
            }
        }
    };


    public ScanSettings getSettings() {
        return mSettings;
    }

    public void setSettings(ScanSettings settings) {
        mSettings = settings;
    }

    public List<ScanFilter> getFilters() {
        return mFilters;
    }

    public void setFilters(List<ScanFilter> filters) {
        mFilters = filters;
    }
}
