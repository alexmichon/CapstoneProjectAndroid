package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;
import timber.log.Timber;

/**
 * Created by Alex on 18/11/2017.
 */

public abstract class ImuMeasurement {

    private static final String TAG = ImuMeasurement.class.getSimpleName();

    private static final byte IMU_DATA_ACC = 0;
    private static final byte IMU_DATA_GYR = 1;
    private static final byte IMU_DATA_MAG = 2;


    public enum Type {
        ACC, GYR
    }

    private final Type mType;

    public ImuMeasurement(Type type) {
        mType = type;
    }

    public static ImuMeasurement decode(byte[] bytes) {
        Timber.d("Decoding measurement");

        int type = BytesUtils.bytesToInt16(bytes, BytesUtils.BYTES_INT16);
        long timestamp = BytesUtils.bytesToTimestamp(bytes, 2 * BytesUtils.BYTES_INT16);
        int offset = 2 * BytesUtils.BYTES_INT16 + BytesUtils.BYTES_TIMESTAMP;

        switch(type) {
            case IMU_DATA_ACC:
                float accX = BytesUtils.bytesToFloat(bytes, offset + 0 * BytesUtils.BYTES_FLOAT);
                float accY = BytesUtils.bytesToFloat(bytes, offset + 1 * BytesUtils.BYTES_FLOAT);
                float accZ = BytesUtils.bytesToFloat(bytes, offset + 2 * BytesUtils.BYTES_FLOAT);

                return new AccMeasurement(timestamp, accX, accY, accZ);

            case IMU_DATA_GYR:
                float gyrX = BytesUtils.bytesToFloat(bytes, offset + 0 * BytesUtils.BYTES_FLOAT);
                float gyrY = BytesUtils.bytesToFloat(bytes, offset + 1 * BytesUtils.BYTES_FLOAT);
                float gyrZ = BytesUtils.bytesToFloat(bytes, offset + 2 * BytesUtils.BYTES_FLOAT);

                return new GyrMeasurement(timestamp, gyrX, gyrY, gyrZ);
        }

        return null;
    }
}
