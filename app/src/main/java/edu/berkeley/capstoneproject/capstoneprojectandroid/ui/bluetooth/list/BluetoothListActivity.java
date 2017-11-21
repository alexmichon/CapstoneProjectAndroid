package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.polidea.rxandroidble.RxBleDevice;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.TrainingActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 07/11/2017.
 */

public class BluetoothListActivity extends ToolbarActivity implements BluetoothListContract.View {

    private static final String TAG = BluetoothListActivity.class.getSimpleName();

    @Inject
    BluetoothListContract.Presenter<BluetoothListContract.View, BluetoothListContract.Interactor> mPresenter;

    //@BindView(R.id.bluetooth_list_paired)
    ListView mPairedList;
    @BindView(R.id.bluetooth_list_scanned)
    ListView mScannedList;


    ProgressBar mScannedProgressBar;

    private BluetoothListAdapter mPairedAdapter;
    private BluetoothListAdapter mScannedAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);

        mPairedAdapter = new BluetoothListAdapter(this, R.layout.bluetooth_device);
        mPairedList.setAdapter(mPairedAdapter);
        mPairedList.setOnItemClickListener(mOnDeviceClickListener);

        mScannedAdapter = new BluetoothListAdapter(this, R.layout.bluetooth_device);
        mScannedList.setAdapter(mScannedAdapter);
        mScannedList.setOnItemClickListener(mOnDeviceClickListener);

        mPresenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onLoadPairedDevices();
        mPresenter.onStartScanning();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onStopScanning();
        cleanPairedDevices();
        cleanScannedDevices();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void promptBluetooth() {

    }

    @Override
    public void addScannedDevice(Rx2BleDevice device) {
        mScannedAdapter.add(device);
        mScannedAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPairedDevice(Rx2BleDevice device) {
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
    public void onDeviceConnected() {

    }

    public void startTrainingActivity() {
        Intent intent = new Intent(BluetoothListActivity.this, TrainingActivity.class);
        startActivity(intent);
        finish();
    }

    private AdapterView.OnItemClickListener mOnDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mPresenter.onDeviceClick((Rx2BleDevice)adapterView.getItemAtPosition(i));
        }
    };
}
