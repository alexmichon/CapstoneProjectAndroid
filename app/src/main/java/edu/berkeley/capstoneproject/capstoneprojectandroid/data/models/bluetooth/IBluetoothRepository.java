package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth;

import android.bluetooth.BluetoothDevice;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.IRepository;
import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface IBluetoothRepository extends IRepository<BluetoothDevice> {

    Observable<BluetoothDevice> getPairedDevices();
    Observable<BluetoothDevice> getScannedDevices();
}
