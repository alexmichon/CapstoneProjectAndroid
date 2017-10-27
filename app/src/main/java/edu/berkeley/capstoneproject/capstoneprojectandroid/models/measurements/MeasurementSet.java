package edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.data.MeasurementData;

/**
 * Created by Alex on 25/10/2017.
 */

public class MeasurementSet<T extends MeasurementData> extends ArrayList<Measurement<T>> {

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

    public synchronized void newMeasurement(long tookAt, T value) {
        add(new Measurement<T>(tookAt, value));
    }

    public synchronized Measurement<T> getLastMeasurement() {
        return get(size()-1);
    }

    public Map<String, ArrayList<Entry>> toEntries() {
        Map<String, ArrayList<Entry>> allEntries = new HashMap<>();
        for (Measurement m: this) {
            Map<String, Entry> entries = m.toEntries();
            for (String label: entries.keySet()) {
                if (!allEntries.containsKey(label)) {
                    allEntries.put(label, new ArrayList<Entry>());

                }
                allEntries.get(label).add(entries.get(label));
            }
        }

        return allEntries;
    }
}
