package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model;

/**
 * Created by Alex on 18/11/2017.
 */

public class Value {

    private static final String TAG = Value.class.getSimpleName();

    private final String mLabel;
    private final float mValue;

    public Value(String label, float value) {
        mLabel = label;
        mValue = value;
    }

    public String getLabel() {
        return mLabel;
    }

    public float getValue() {
        return mValue;
    }
}
