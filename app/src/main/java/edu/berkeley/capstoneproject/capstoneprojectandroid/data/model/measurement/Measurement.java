package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Encoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Gyroscope;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.BytesUtils;

/**
 * Created by Alex on 18/11/2017.
 */

public class Measurement {

    private final long mTimestamp;
    private final float mValue;

    private final Metric mMetric;

    private final Exercise mExercise;
    private int mId;

    public Measurement(Exercise exercise, Metric metric, long timestamp, float value) {
        mMetric = metric;
        mTimestamp = timestamp;
        mValue = value;
        mExercise = exercise;
        //exercise.addMeasurement(this);
    }


    public long getTimestamp() {
        return mTimestamp;
    }
    public float getValue() { return mValue; }

    public Metric getMetric() {
        return mMetric;
    }

    public Exercise getExercise() {
        return mExercise;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }
}
