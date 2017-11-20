package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

/**
 * Created by Alex on 19/11/2017.
 */

public class Encoder extends Sensor {

    public static final int ID_ANGLE = 7;

    private static final String NAME_ANGLE = "Angle";

    public Encoder(int id, String name) {
        super(id, name);
        setMetrics(new Metric[] {
                new Metric(this, ID_ANGLE, NAME_ANGLE)
        });
    }
}
