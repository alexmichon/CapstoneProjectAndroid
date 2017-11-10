package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth;

import android.bluetooth.BluetoothDevice;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.Repository;
import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface BluetoothRepository extends Repository<BluetoothDevice> {

    Observable<BluetoothDevice> getPairedDevices();
    Observable<BluetoothDevice> getScannedDevices();
}
