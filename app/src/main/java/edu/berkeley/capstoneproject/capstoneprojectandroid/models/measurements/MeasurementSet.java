package edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alex on 25/10/2017.
 */

public class MeasurementSet<T> extends ArrayList<Measurement<T>> {

    private static final String TAG = MeasurementSet.class.getSimpleName();

    private String mName;

    public MeasurementSet(String name) {
        mName = name;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void newMeasurement(Date tookAt, T value) {
        add(new Measurement<T>(tookAt, value));
    }
}
