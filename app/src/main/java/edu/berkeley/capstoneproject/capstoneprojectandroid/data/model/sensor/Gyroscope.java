package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

/**
 * Created by Alex on 19/11/2017.
 */

public class Gyroscope extends Sensor {

    public static final int ID_GYR_X = 4;
    public static final int ID_GYR_Y = 5;
    public static final int ID_GYR_Z = 6;

    private static final String NAME_GYR_X = "Acc X";
    private static final String NAME_GYR_Y = "Acc Y";
    private static final String NAME_GYR_Z = "Acc Z";

    public Gyroscope(int id, String name) {
        super(id, name);
        setMetrics(new Metric[] {
                new Metric(this, ID_GYR_X, NAME_GYR_X),
                new Metric(this, ID_GYR_Y, NAME_GYR_Y),
                new Metric(this, ID_GYR_Z, NAME_GYR_Z)
        });
    }
}
