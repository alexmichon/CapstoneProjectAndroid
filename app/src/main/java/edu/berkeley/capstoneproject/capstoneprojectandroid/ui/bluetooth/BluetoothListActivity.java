package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListActivity extends BaseActivity implements BluetoothListContract.View {

    private static final String TAG = BluetoothListActivity.class.getSimpleName();

    private BluetoothListPresenter mPresenter;

    private ListView mPairedList;
    private ListView mScannedList;
    private ProgressBar mScannedProgressBar;

    private BluetoothListAdapter mPairedAdapter;
    private BluetoothListAdapter mScannedAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        mPairedList = (ListView) findViewById(R.id.bluetooth_list_paired);
        mScannedList = (ListView) findViewById(R.id.bluetooth_list_scanned);
        mScannedProgressBar = (ProgressBar) findViewById(R.id.bluetooth_progress_scan);

        mPairedAdapter = new BluetoothListAdapter(this, R.layout.bluetooth_device);
        mScannedAdapter = new BluetoothListAdapter(this, R.layout.bluetooth_device);

        mPairedList.setAdapter(mPairedAdapter);
        mScannedList.setAdapter(mScannedAdapter);

        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        final BluetoothLeAdapter bluetoothLeAdapter;
        if (Build.VERSION.SDK_INT >= 21) {
            bluetoothLeAdapter = new BluetoothLe21Adapter(adapter);
        }
        else {
            bluetoothLeAdapter = new BluetoothLe18Adapter(adapter);
        }

        final BluetoothRepository bluetoothRepository = new BluetoothRepositoryImpl(bluetoothLeAdapter);

        mPresenter = new BluetoothListPresenter(bluetoothRepository);
        mPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getPairedDevices();
        mPresenter.startDiscovery();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.cancelDiscovery();
        cleanPairedDevices();
        cleanScannedDevices();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void addScannedDevice(BluetoothDevice device) {
        mScannedAdapter.add(device);
        mScannedAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPairedDevice(BluetoothDevice device) {
        mPairedAdapter.add(device);
        mPairedAdapter.notifyDataSetChanged();
    }

    @Override
    public void cleanScannedDevices() {
        mScannedAdapter.clear();
        mScannedAdapter.notifyDataSetChanged();
    }

    @Override
    public void cleanPairedDevices() {
        mPairedAdapter.clear();
        mPairedAdapter.notifyDataSetChanged();
    }

    @Override
    public void showScanningProgress() {
        mScannedProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideScanningProgress() {
        mScannedProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(BluetoothListActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
