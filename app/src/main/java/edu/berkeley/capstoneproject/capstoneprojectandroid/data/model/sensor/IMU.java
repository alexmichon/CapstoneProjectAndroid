package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

/**
 * Created by Alex on 19/11/2017.
 */

public class IMU extends Sensor {

    private static final String NAME = "IMU";

    private static final Metric[] METRICS = {
    };

    public IMU(int id, String name) {
        super(id, name);
    }
}
