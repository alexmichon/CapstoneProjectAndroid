package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;

/**
 * Created by Alex on 16/02/2018.
 */

public class Magnetometer extends Sensor {

    public static final int ID_MAG_X = 7;
    public static final int ID_MAG_Y = 8;
    public static final int ID_MAG_Z = 9;

    private static final String NAME_MAG_X = "MAG X";
    private static final String NAME_MAG_Y = "MAG Y";
    private static final String NAME_MAG_Z = "MAG Z";

    public Magnetometer(int id, String name) {
        super(id, name);
        setMetrics(new Metric[] {
                new Metric(ID_MAG_X, NAME_MAG_X, this),
                new Metric(ID_MAG_Y, NAME_MAG_Y, this),
                new Metric(ID_MAG_Z, NAME_MAG_Z, this)
        });
    }
}
