package edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements;

import java.util.Date;

/**
 * Created by Alex on 25/10/2017.
 */

public class Measurement<T> {

    private static final String TAG = Measurement.class.getSimpleName();

    private final T mValue;
    private final long mTookAt;

    public Measurement(long tookAt, T value) {
        mTookAt = tookAt;
        mValue = value;
    }

    public T getValue() {
        return mValue;
    }

    public long tookAt() {
        return mTookAt;
    }
}
