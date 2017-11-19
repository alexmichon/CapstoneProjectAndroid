package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.bluetooth;

import android.bluetooth.BluetoothDevice;

import com.polidea.rxandroidble.RxBleDevice;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.IRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;

/**
 * Created by Alex on 07/11/2017.
 */

public interface IBluetoothRepository extends IRepository<BluetoothDevice> {

    Observable<Rx2BleDevice> getPairedDevices();
    Observable<Rx2BleDevice> getScannedDevices();
}
