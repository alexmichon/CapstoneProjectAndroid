package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.ble;

import java.util.UUID;

import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.BluetoothConstants;

/**
 * Created by Alex on 17/11/2017.
 */

public class BleDeviceValidator {

    public static boolean validate(UUID serviceUuid) {
        return BluetoothConstants.UUID_SERVICE.equals(serviceUuid);
    }
}
