package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import edu.berkeley.capstoneproject.capstoneprojectandroid.activities.ImuActivity;

/**
 * Created by Alex on 25/10/2017.
 */

public class IMU extends Sensor<Float> {

    private static final String TAG = IMU.class.getSimpleName();

    private static final String DEFAULT_NAME = "IMU";

    public IMU() {
        super(SensorType.IMU, DEFAULT_NAME);
    }

    public IMU(String name) {
        super(SensorType.IMU, name);
    }

}
