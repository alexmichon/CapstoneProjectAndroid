package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import android.bluetooth.BluetoothAdapter;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IConnectionService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IDeviceService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;

/**
 * Created by Alex on 05/12/2017.
 */

public interface BluetoothComponent {

    BluetoothAdapter bluetoothAdapter();

    IDeviceService deviceService();
    IConnectionService connectionService();
    IExerciseService exerciseService();
    IMeasurementService measurementService();
}
