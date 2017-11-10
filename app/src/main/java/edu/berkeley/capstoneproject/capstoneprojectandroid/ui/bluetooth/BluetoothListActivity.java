package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesActivity;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListActivity extends ToolbarActivity implements BluetoothListContract.View {

    private static final String TAG = BluetoothListActivity.class.getSimpleName();

    @Inject
    BluetoothListContract.Presenter mPresenter;

    private ListView mPairedList;
    private ListView mScannedList;
    private ProgressBar mScannedProgressBar;

    private BluetoothListAdapter mPairedAdapter;
    private BluetoothListAdapter mScannedAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        mPairedList = (ListView) findViewById(R.id.bluetooth_list_paired);
        mPairedAdapter = new BluetoothListAdapter(this, R.layout.bluetooth_device);
        mPairedList.setAdapter(mPairedAdapter);
        mPairedList.setOnItemClickListener(mOnDeviceClickListener);

        mScannedList = (ListView) findViewById(R.id.bluetooth_list_scanned);
        mScannedAdapter = new BluetoothListAdapter(this, R.layout.bluetooth_device);
        mScannedList.setAdapter(mScannedAdapter);
        mScannedList.setOnItemClickListener(mOnDeviceClickListener);

        mScannedProgressBar = (ProgressBar) findViewById(R.id.bluetooth_progress_scan);
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

    @Override
    public void startExercisesActivity(BluetoothDevice device) {
        Intent intent = new Intent(BluetoothListActivity.this, ExerciseTypesActivity.class);
        startActivity(intent);
        finish();
    }

    private AdapterView.OnItemClickListener mOnDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mPresenter.onDeviceClick((BluetoothDevice)adapterView.getItemAtPosition(i));
        }
    };
}
