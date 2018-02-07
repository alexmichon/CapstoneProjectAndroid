package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;

/**
 * Created by Alex on 19/11/2017.
 */

public class Gyroscope extends Sensor {

    public static final int ID_GYR_X = 4;
    public static final int ID_GYR_Y = 5;
    public static final int ID_GYR_Z = 6;

    private static final String NAME_GYR_X = "Gyr X";
    private static final String NAME_GYR_Y = "Gyr Y";
    private static final String NAME_GYR_Z = "Gyr Z";

    public Gyroscope(int id, String name) {
        super(id, name);
        setMetrics(new Metric[] {
                new Metric(ID_GYR_X, NAME_GYR_X, this),
                new Metric(ID_GYR_Y, NAME_GYR_Y, this),
                new Metric(ID_GYR_Z, NAME_GYR_Z, this)
        });
    }
}
