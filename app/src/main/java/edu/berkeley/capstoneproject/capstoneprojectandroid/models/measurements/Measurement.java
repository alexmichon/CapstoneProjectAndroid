package edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements;

import java.util.Date;

/**
 * Created by Alex on 25/10/2017.
 */

public class Measurement<T> {

    private static final String TAG = Measurement.class.getSimpleName();

    private final T mValue;
    private final Date mTookAt;

    public Measurement(Date tookAt, T value) {
        mTookAt = tookAt;
        mValue = value;
    }

    public T getValue() {
        return mValue;
    }

    public Date tookAt() {
        return mTookAt;
    }
}
