package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;

/**
 * Created by Alex on 19/11/2017.
 */

public class Accelerometer extends Sensor {

    public static final int ID_ACC_X = 1;
    public static final int ID_ACC_Y = 2;
    public static final int ID_ACC_Z = 3;

    private static final String NAME_ACC_X = "Acc X";
    private static final String NAME_ACC_Y = "Acc Y";
    private static final String NAME_ACC_Z = "Acc Z";

    public Accelerometer(int id, String name) {
        super(id, name);
        setMetrics(new Metric[] {
                new Metric(this, ID_ACC_X, NAME_ACC_X),
                new Metric(this, ID_ACC_Y, NAME_ACC_Y),
                new Metric(this, ID_ACC_Z, NAME_ACC_Z)
        });
    }
}
