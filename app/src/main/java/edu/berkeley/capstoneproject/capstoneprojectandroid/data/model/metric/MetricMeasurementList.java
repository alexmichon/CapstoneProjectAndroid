package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricMeasurementList {

    private final Metric mMetric;
    private List<Measurement> mMeasurements;

    public MetricMeasurementList(Metric metric) {
        mMetric = metric;
        mMeasurements = new ArrayList<>();
    }

    public MetricMeasurementList(Metric metric, List<Measurement> measurements) {
        mMetric = metric;
        mMeasurements = measurements;
    }

    public Metric getMetric() {
        return mMetric;
    }

    public List<Measurement> getMeasurements() {
        return mMeasurements;
    }

    public int getSize() {
        return mMeasurements.size();
    }

    public void addMeasurement(Measurement measurement) {
        mMeasurements.add(measurement);
    }
}
