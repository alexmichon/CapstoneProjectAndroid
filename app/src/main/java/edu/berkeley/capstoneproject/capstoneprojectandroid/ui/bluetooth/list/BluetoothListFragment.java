package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesAdapter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 18/11/2017.
 */

public class BluetoothListFragment extends BaseFragment implements BluetoothListContract.View {

    private static final String TAG = BluetoothListFragment.class.getSimpleName();

    private static final String TITLE = "Bluetooth Devices";

    @Inject
    BluetoothListContract.Presenter<BluetoothListContract.View, BluetoothListContract.Interactor> mPresenter;

//    @BindView(R.id.bluetooth_list_paired)
//    ListView mPairedList;
    @BindView(R.id.bluetooth_list_scanned)
    ListView mScannedList;


    ProgressBar mScannedProgressBar;

//    private BluetoothListAdapter mPairedAdapter;
    private BluetoothListAdapter mScannedAdapter;

    private BluetoothListFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bluetooth, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);

            mScannedAdapter = new BluetoothListAdapter(getContext(), R.layout.bluetooth_device);
            mScannedList.setAdapter(mScannedAdapter);

            mScannedProgressBar = new ProgressBar(getContext());
            mScannedProgressBar.setIndeterminate(true);
            mScannedProgressBar.setVisibility(View.GONE);

            mScannedList.addFooterView(mScannedProgressBar);

            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onLoadPairedDevices();
        mPresenter.onStartScanning();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onStopScanning();
        cleanPairedDevices();
        cleanScannedDevices();
    }

    @Override
    public void addScannedDevice(Rx2BleDevice device) {
        mScannedAdapter.add(device);
        mScannedAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPairedDevice(Rx2BleDevice device) {
//        mPairedAdapter.add(device);
//        mPairedAdapter.notifyDataSetChanged();
    }

    @Override
    public void cleanScannedDevices() {
        mScannedAdapter.clear();
        mScannedAdapter.notifyDataSetChanged();
    }

    @Override
    public void cleanPairedDevices() {
//        mPairedAdapter.clear();
//        mPairedAdapter.notifyDataSetChanged();
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
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeviceConnected() {
        if (mListener != null) {
            mListener.onDeviceConnected();
        }
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @OnItemClick(R.id.bluetooth_list_scanned)
    void OnDeviceClickListener(int position) {
        Rx2BleDevice device = mScannedAdapter.getItem(position);
        mPresenter.onDeviceClick(device);
    };



    public void setListener(BluetoothListFragmentListener listener) {
        mListener = listener;
    }

    public interface BluetoothListFragmentListener {
        void onDeviceConnected();
    }
}
