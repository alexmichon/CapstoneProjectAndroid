package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

import android.util.Log;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 25/10/2017.
 */

public class IMU extends Sensor<IMUValue> {

    private static final String TAG = IMU.class.getSimpleName();

    private static final String DEFAULT_NAME = "IMU";

    public IMU() {
        super(SensorType.IMU, DEFAULT_NAME);
    }

    public IMU(String name) {
        super(SensorType.IMU, name);
    }


    public static Measurement<IMUValue> decodeMeasurement(byte[] bytes) {
        Log.d(TAG, "Decoding measurement");

        long took_at = BytesUtils.bytesToDate(bytes);

        int accX = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP);
        int accY = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + BytesUtils.BYTES_INT16);
        int accZ = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + BytesUtils.BYTES_INT16 + BytesUtils.BYTES_INT16);

        IMUValue imuValue = new IMUValue(accX, accY, accZ);

        return new Measurement<IMUValue>(took_at, imuValue);
    }

}