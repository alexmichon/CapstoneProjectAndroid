package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

/**
 * Created by Alex on 18/11/2017.
 */

public class GyrMeasurement extends ImuMeasurement {

    private static final String TAG = GyrMeasurement.class.getSimpleName();

    public static final String LABEL_GYR_X = "Gyr X";
    public static final String LABEL_GYR_Y = "Gyr Y";
    public static final String LABEL_GYR_Z = "Gyr Z";

    private final long mTimestamp;
    private final float mGyrX, mGyrY, mGyrZ;

    public GyrMeasurement(long timestamp, float gyrX, float gyrY, float gyrZ) {
        super(Type.GYR);
        mTimestamp = timestamp;
        mGyrX = gyrX;
        mGyrY = gyrY;
        mGyrZ = gyrZ;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public float getGyrX() {
        return mGyrX;
    }

    public float getGyrY() {
        return mGyrY;
    }

    public float getGyrZ() {
        return mGyrZ;
    }
}
