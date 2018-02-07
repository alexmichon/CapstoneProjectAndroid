package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;

/**
 * Created by Alex on 19/11/2017.
 */

public class Metric {

    private final int mID;

    private final Sensor mSensor;
    private final String mName;

    private float mMin;
    private float mMax;

    public Metric(int id, String name, Sensor sensor) {
        mID = id;
        mName = name;
        mSensor = sensor;
    }

    public int getId() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public Sensor getSensor() {
        return mSensor;
    }

    public float getMin() {
        return mMin;
    }

    public void setMin(float min) {
        mMin = min;
    }

    public float getMax() {
        return mMax;
    }

    public void setMax(float max) {
        mMax = max;
    }
}
