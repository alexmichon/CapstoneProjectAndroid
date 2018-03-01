package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import android.bluetooth.BluetoothAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IBaseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class BluetoothHelper implements IBluetoothHelper {

    private final BluetoothAdapter mBluetoothAdapter;

    private final IDeviceService mDeviceService;
    private final IConnectionService mConnectionService;
    private final IExerciseService mExerciseService;
    private final IMeasurementService mMeasurementService;

    private Rx2BleDevice mDevice;
    private Rx2BleConnection mConnection;

    @Inject
    public BluetoothHelper(BluetoothAdapter adapter, IDeviceService deviceService, IConnectionService connectionService, IExerciseService exerciseService, IMeasurementService measurementService) {
        mBluetoothAdapter = adapter;

        mDeviceService = deviceService;
        mConnectionService = connectionService;
        mExerciseService = exerciseService;
        mMeasurementService = measurementService;
    }

    @Override
    public IConnectionService getConnectionService() {
        return mConnectionService;
    }

    @Override
    public IDeviceService getDeviceService() {
        return mDeviceService;
    }

    @Override
    public IExerciseService getExerciseService() {
        return mExerciseService;
    }

    public IMeasurementService getMeasurementService() {
        return mMeasurementService;
    }

    @Override
    public boolean getBluetoothStatus() {
        return (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled());
    }

}
