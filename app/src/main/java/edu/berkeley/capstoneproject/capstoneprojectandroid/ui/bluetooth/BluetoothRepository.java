package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import android.bluetooth.BluetoothDevice;

import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.Repository;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 07/11/2017.
 */

public interface BluetoothRepository extends Repository<BluetoothDevice> {

    Observable<BluetoothDevice> getPairedDevices();
    Observable<BluetoothDevice> getScannedDevices();
}
