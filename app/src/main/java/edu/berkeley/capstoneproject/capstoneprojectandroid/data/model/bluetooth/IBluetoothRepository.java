package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.bluetooth;

import android.bluetooth.BluetoothDevice;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.IRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface IBluetoothRepository extends IRepository<BluetoothDevice> {

    Observable<Rx2BleDevice> getPairedDevices();
    Observable<Rx2BleDevice> getScannedDevices();
}