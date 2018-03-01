package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 18/11/2017.
 */

public class BluetoothListFragment extends BaseFragment<BluetoothListContract.View, BluetoothListContract.Presenter<BluetoothListContract.View, BluetoothListContract.Interactor>> implements BluetoothListContract.View {

    private static final int REQUEST_ENABLE_BT = 0;

    @BindView(R.id.bluetooth_list_scanned)
    ListView mScannedList;


    private ProgressBar mScannedProgressBar;

    private BluetoothListAdapter mScannedAdapter;

    private BluetoothListFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bluetooth, container, false);

        ButterKnife.bind(this, view);

        mScannedAdapter = new BluetoothListAdapter(getContext(), R.layout.bluetooth_device);
        mScannedList.setAdapter(mScannedAdapter);

        mScannedProgressBar = new ProgressBar(getContext());
        mScannedProgressBar.setIndeterminate(true);
        mScannedProgressBar.setVisibility(View.GONE);

        mScannedList.addFooterView(mScannedProgressBar);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                getPresenter().startScanning();
            }
        }

    }

    @NonNull
    @Override
    public BluetoothListContract.Presenter<BluetoothListContract.View, BluetoothListContract.Interactor> createPresenter() {
        return getActivityComponent().bluetoothListPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().stopScanning();
        cleanPairedDevices();
        cleanScannedDevices();
    }

    @Override
    public void promptBluetooth() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    @Override
    public void addScannedDevice(Rx2BleDevice device) {
        mScannedAdapter.add(device);
        mScannedAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPairedDevice(Rx2BleDevice device) {

    }

    @Override
    public void cleanScannedDevices() {
        mScannedAdapter.clear();
        mScannedAdapter.notifyDataSetChanged();
    }

    @Override
    public void cleanPairedDevices() {

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
    public void onDeviceConnecting() {
        showLoading();
    }

    @Override
    public void onDeviceConnected() {
        hideLoading();
        if (mListener != null) {
            mListener.onBluetoothDeviceSelected();
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @OnItemClick(R.id.bluetooth_list_scanned)
    void OnDeviceClickListener(int position) {
        if (position < mScannedAdapter.getCount()) {
            Rx2BleDevice device = mScannedAdapter.getItem(position);
            getPresenter().onDeviceSelected(device);
        }
    };



    public void setListener(BluetoothListFragmentListener listener) {
        mListener = listener;
    }

    public interface BluetoothListFragmentListener {
        void onBluetoothDeviceSelected();
    }
}
