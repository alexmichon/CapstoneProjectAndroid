package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

import android.icu.util.Measure;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.MeasurementSet;

/**
 * Created by Alex on 25/10/2017.
 */

public abstract class Sensor<T> {

    private static final String TAG = Sensor.class.getSimpleName();

    private static int ID = 0;


    public enum SensorType {
        IMU, ENCODER
    }


    private final int mId;
    private final String mName;
    private final SensorType mType;
    private final List<MeasurementSet<T>> mMeasurementSets = new ArrayList<>();

    public Sensor (SensorType type, String name) {
        mId = ID++;
        mType = type;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public SensorType getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    protected String generateMeasurementSetName() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        return mName + "_" + dt.format(new Date());
    }

    public List<MeasurementSet<T>> getMeasurementSets() {
        return mMeasurementSets;
    }

    public MeasurementSet<T> newMeasurementSet(String name) {
        MeasurementSet<T> m = new MeasurementSet<T>(name);
        mMeasurementSets.add(m);

        return m;
    }

    public MeasurementSet<T> getMeasurementSet(String name) {
        for (MeasurementSet m: mMeasurementSets) {
            if (m.getName().equals(name)) {
                return m;
            }
        }

        return null;
    }

    public MeasurementSet<T> getCurrentMeasurementSet() {
        if (mMeasurementSets.isEmpty()) {
            return null;
        }
        return mMeasurementSets.get(mMeasurementSets.size() - 1);
    }

    public void addMeasurement(Measurement<T> meas) {
        Log.d(TAG, mName + ": Add measurement");
        MeasurementSet<T> measurementSet = getCurrentMeasurementSet();
        if (measurementSet == null) {
            measurementSet = newMeasurementSet(generateMeasurementSetName());
        }

        measurementSet.add(meas);
    }

    public void newMeasurement(long tookAt, T value) {
        Log.d(TAG, mName + ": New measurement");
        MeasurementSet<T> measurementSet = getCurrentMeasurementSet();
        if (measurementSet == null) {
            measurementSet = newMeasurementSet(generateMeasurementSetName());
        }

        measurementSet.newMeasurement(tookAt, value);
    }



    @Override
    public String toString() {
        return mName;
    }
}
