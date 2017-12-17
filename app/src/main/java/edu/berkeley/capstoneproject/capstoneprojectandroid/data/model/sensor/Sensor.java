package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;

/**
 * Created by Alex on 19/11/2017.
 */

public abstract class Sensor {

    private final int mID;
    private final String mName;

    private Metric[] mMetrics;

    public Sensor(int id, String name) {
        mID = id;
        mName = name;
    }


    public int getId() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public void setMetrics(Metric[] metrics) {
        mMetrics = metrics;
    }

    public Metric[] getMetrics() {
        return mMetrics;
    }

    public Metric getMetric(int id) {
        for (Metric m: mMetrics) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public Metric getMetric(String name) {
        for (Metric m: mMetrics) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mName;
    }
}
