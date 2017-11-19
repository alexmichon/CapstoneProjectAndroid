package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

/**
 * Created by Alex on 18/11/2017.
 */

public class AccMeasurement extends ImuMeasurement {

    private static final String TAG = AccMeasurement.class.getSimpleName();

    public static final String LABEL_ACC_X = "Acc X";
    public static final String LABEL_ACC_Y = "Acc Y";
    public static final String LABEL_ACC_Z = "Acc Z";

    private final long mTimestamp;
    private final float mAccX, mAccY, mAccZ;

    public AccMeasurement(long timestamp, float accX, float accY, float accZ) {
        super(Type.ACC);
        mTimestamp = timestamp;
        mAccX = accX;
        mAccY = accY;
        mAccZ = accZ;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public float getAccX() {
        return mAccX;
    }

    public float getAccY() {
        return mAccY;
    }

    public float getAccZ() {
        return mAccZ;
    }
}
