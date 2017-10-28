package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.data.ImuData;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 25/10/2017.
 */

public class IMU extends Sensor {

    private static final String TAG = IMU.class.getSimpleName();

    private static final String DEFAULT_NAME = "IMU";

    public static final String LABEL_IMU_ACC_X = "Acc X";
    public static final String LABEL_IMU_ACC_Y = "Acc Y";
    public static final String LABEL_IMU_ACC_Z = "Acc Z";

    public IMU() {
        super(Sensor.SensorType.IMU, DEFAULT_NAME);
    }

    public IMU(String name) {
        super(Sensor.SensorType.IMU, name);
    }


    public static Map<String, Measurement> decodeMeasurement(byte[] bytes) {
        Log.d(TAG, "Decoding measurement");

        Map<String, Measurement> measurements = new HashMap<>(3);

        long took_at = BytesUtils.bytesToDate(bytes);

        int accX = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP);
        int accY = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + BytesUtils.BYTES_INT16);
        int accZ = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + BytesUtils.BYTES_INT16 + BytesUtils.BYTES_INT16);

        measurements.put(LABEL_IMU_ACC_X, new Measurement<Integer>(took_at, accX));
        measurements.put(LABEL_IMU_ACC_Y, new Measurement<Integer>(took_at, accY));
        measurements.put(LABEL_IMU_ACC_Z, new Measurement<Integer>(took_at, accZ));

        return measurements;
    }

}