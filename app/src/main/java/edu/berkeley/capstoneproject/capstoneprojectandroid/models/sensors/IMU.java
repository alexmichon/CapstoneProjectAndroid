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

    private static final byte IMU_DATA_ACC = 0;
    private static final byte IMU_DATA_GYR = 1;
    private static final byte IMU_DATA_MAG = 2;


    public static final String LABEL_IMU_ACC_X = "Acc X";
    public static final String LABEL_IMU_ACC_Y = "Acc Y";
    public static final String LABEL_IMU_ACC_Z = "Acc Z";
    public static final String LABEL_IMU_GYR_X = "Gyr X";
    public static final String LABEL_IMU_GYR_Y = "Gyr Y";
    public static final String LABEL_IMU_GYR_Z = "Gyr Z";
    public static final String LABEL_IMU_MAG_X = "Mag X";
    public static final String LABEL_IMU_MAG_Y = "Mag Y";
    public static final String LABEL_IMU_MAG_Z = "Mag Z";


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

        int type = bytes[BytesUtils.BYTES_TIMESTAMP];

        switch(type) {
            case IMU_DATA_ACC:
                int accX = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 1 * BytesUtils.BYTES_INT16);
                int accY = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 2 * BytesUtils.BYTES_INT16);
                int accZ = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 3 * BytesUtils.BYTES_INT16);
                measurements.put(LABEL_IMU_ACC_X, new Measurement<Integer>(took_at, accX));
                measurements.put(LABEL_IMU_ACC_Y, new Measurement<Integer>(took_at, accY));
                measurements.put(LABEL_IMU_ACC_Z, new Measurement<Integer>(took_at, accZ));
                break;
            case IMU_DATA_GYR:
                int gyrX = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 1 * BytesUtils.BYTES_INT16);
                int gyrY = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 2 * BytesUtils.BYTES_INT16);
                int gyrZ = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 3 * BytesUtils.BYTES_INT16);
                measurements.put(LABEL_IMU_GYR_X, new Measurement<Integer>(took_at, gyrX));
                measurements.put(LABEL_IMU_GYR_Y, new Measurement<Integer>(took_at, gyrY));
                measurements.put(LABEL_IMU_GYR_Z, new Measurement<Integer>(took_at, gyrZ));
                break;
            case IMU_DATA_MAG:
                int magX = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 1 * BytesUtils.BYTES_INT16);
                int magY = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 2 * BytesUtils.BYTES_INT16);
                int magZ = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_TIMESTAMP + 3 * BytesUtils.BYTES_INT16);
                measurements.put(LABEL_IMU_MAG_X, new Measurement<Integer>(took_at, magX));
                measurements.put(LABEL_IMU_MAG_Y, new Measurement<Integer>(took_at, magY));
                measurements.put(LABEL_IMU_MAG_Z, new Measurement<Integer>(took_at, magZ));
                break;
        }

        return measurements;
    }

}