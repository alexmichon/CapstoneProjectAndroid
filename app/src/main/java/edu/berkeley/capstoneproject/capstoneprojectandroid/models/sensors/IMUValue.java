package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

/**
 * Created by Alex on 26/10/2017.
 */

public class IMUValue {

    private static final String TAG = IMUValue.class.getSimpleName();

    private final int mAccX;
    private final int mAccY;
    private final int mAccZ;


    public IMUValue(int accX, int accY, int accZ) {
        mAccX = accX;
        mAccY = accY;
        mAccZ = accZ;
    }


    public int getAccX() {
        return mAccX;
    }

    public int getAccY() {
        return mAccY;
    }

    public int getAccZ() {
        return mAccZ;
    }
}
