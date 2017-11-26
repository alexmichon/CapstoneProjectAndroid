package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.connection.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.device.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.exercise.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.measurement.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public interface IBluetoothHelper {

    Rx2BleDevice getDevice();
    void setDevice(Rx2BleDevice device);

    Rx2BleConnection getConnection();
    void setConnection(Rx2BleConnection connection);

    IConnectionService getConnectionService();

    IDeviceService getDeviceService();

    IExerciseService getExerciseService();

    IMeasurementService getMeasurementService();
}
