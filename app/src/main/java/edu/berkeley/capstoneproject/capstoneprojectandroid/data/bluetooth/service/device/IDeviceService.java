package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.device;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.base.IBaseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;
import io.reactivex.Observable;

/**
 * Created by Alex on 26/11/2017.
 */

public interface IDeviceService extends IBaseService {

    Observable<Rx2BleDevice> getPairedDevices();
    Observable<Rx2BleDevice> getScannedDevices();

}
