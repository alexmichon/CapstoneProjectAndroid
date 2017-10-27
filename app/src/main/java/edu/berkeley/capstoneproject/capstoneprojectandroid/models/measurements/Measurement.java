package edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements;

import com.github.mikephil.charting.data.Entry;

import java.util.HashMap;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.data.MeasurementData;

/**
 * Created by Alex on 25/10/2017.
 */

public class Measurement<T extends MeasurementData> {

    private static final String TAG = Measurement.class.getSimpleName();

    private final T mData;
    private final long mTookAt;

    public Measurement(long tookAt, T data) {
        mTookAt = tookAt;
        mData = data;
    }

    public T getData() {
        return mData;
    }

    public long tookAt() {
        return mTookAt;
    }

    public Map<String, Entry> toEntries () {
        Map<String, Entry> entries = new HashMap<>();

        Map<String, Float> values = mData.getValues();
        for (String label: values.keySet()) {
            entries.put(label, new Entry(new Float(mTookAt), values.get(label)));
        }

        return entries;
    }
}
