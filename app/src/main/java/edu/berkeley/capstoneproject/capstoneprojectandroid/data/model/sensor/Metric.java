package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;

/**
 * Created by Alex on 19/11/2017.
 */

public class Metric {

    private final Sensor mSensor;
    private final String mName;
    private final List<Measurement> mMeasurements = new ArrayList<>();

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

    public List<Measurement> getMeasurements() {
        return mMeasurements;
    }

    public int getId() {
        return mID;
    }
}
