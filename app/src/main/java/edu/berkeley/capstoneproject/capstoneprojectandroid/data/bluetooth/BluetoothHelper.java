package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

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

    private final List<IBaseService> mServices = new ArrayList<>();

    private final IDeviceService mDeviceService;
    private final IConnectionService mConnectionService;
    private final IExerciseService mExerciseService;
    private final IMeasurementService mMeasurementService;

    private Rx2BleDevice mDevice;
    private Rx2BleConnection mConnection;

    @Inject
    public BluetoothHelper(IDeviceService deviceService, IConnectionService connectionService, IExerciseService exerciseService, IMeasurementService measurementService) {
        mDeviceService = deviceService;
        mConnectionService = connectionService;
        mExerciseService = exerciseService;
        mMeasurementService = measurementService;

        mServices.add(mDeviceService);
        mServices.add(mConnectionService);
        mServices.add(mExerciseService);
        mServices.add(mMeasurementService);
    }


    @Override
    public Rx2BleDevice getDevice() {
        return mDevice;
    }

    @Override
    public void setDevice(Rx2BleDevice device) {
        mDevice = device;
        for (IBaseService service: mServices) {
            service.setDevice(device);
        }
    }

    @Override
    public Rx2BleConnection getConnection() {
        return mConnection;
    }

    @Override
    public void setConnection(Rx2BleConnection connection) {
        mConnection = connection;
        for (IBaseService service: mServices) {
            service.setConnection(connection);
        }
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

}
