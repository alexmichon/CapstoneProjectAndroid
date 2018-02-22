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

    private final int mBatch;

    private final long mTimestamp;
    private final float mValue;

    private final int mSensorId;
    private final int mMetricId;

    private final Exercise mExercise;
    private int mId;

    public Measurement(Exercise exercise, int sensorId, int metricId, int batch, long timestamp, float value) {
        mExercise = exercise;
        mSensorId = sensorId;
        mMetricId = metricId;
        mBatch = batch;
        mTimestamp = timestamp;
        mValue = value;
        //exercise.addMeasurement(this);
    }


    public long getTimestamp() {
        return mTimestamp;
    }
    public float getValue() { return mValue; }

    public int getSensorId() {
        return mSensorId;
    }

    public int getMetricId() {
        return mMetricId;
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

    public int getBatch() {
        return mBatch;
    }
}
