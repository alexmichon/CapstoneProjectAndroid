package edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Alex on 25/10/2017.
 */

public class Measurement<T extends Number> {

    private static final String TAG = Measurement.class.getSimpleName();

    private final T mValue;
    private final long mTookAt;

    private int mID;

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

    public int getID() {
        return mID;
    }

    public void setID(int id) {
        mID = id;
    }

    public JSONObject toJson() throws JSONException {
        return new JSONObject()
                .put("measurement", new JSONObject()
                    .put("took_at", mTookAt)
                    .put("value", mValue)
                );
    }
}
