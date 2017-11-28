package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleConnection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble.Rx2BleDevice;

/**
 * Created by Alex on 26/11/2017.
 */

public class BaseService implements IBaseService {

    private Rx2BleConnection mConnection;
    private Rx2BleDevice mDevice;

    public Rx2BleConnection getConnection() {
        return mConnection;
    }

    public void setConnection(Rx2BleConnection connection) {
        mConnection = connection;
    }

    public Rx2BleDevice getDevice() {
        return mDevice;
    }

    public void setDevice(Rx2BleDevice device) {
        mDevice = device;
    }
}
