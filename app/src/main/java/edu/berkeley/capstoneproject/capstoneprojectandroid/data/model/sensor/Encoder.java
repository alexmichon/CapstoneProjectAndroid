package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;

/**
 * Created by Alex on 19/11/2017.
 */

public class Encoder extends Sensor {

    public static final int ID_ANGLE = 7;

    private static final String NAME_ANGLE = "Angle";

    public Encoder(int id, String name) {
        super(id, name);
        setMetrics(new Metric[] {
                new Metric(ID_ANGLE, NAME_ANGLE, this)
        });
    }
}
