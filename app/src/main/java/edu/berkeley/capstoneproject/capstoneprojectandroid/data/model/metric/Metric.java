package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;

/**
 * Created by Alex on 19/11/2017.
 */

public class Metric {

    private final Sensor mSensor;
    private final String mName;

    private final int mID;

    public Metric(Sensor sensor, int id, String name) {
        mSensor = sensor;
        mID = id;
        mName = name;
    }

    public Sensor getSensor() {
        return mSensor;
    }

    public String getName() {
        return mName;
    }

    public int getId() {
        return mID;
    }
}
