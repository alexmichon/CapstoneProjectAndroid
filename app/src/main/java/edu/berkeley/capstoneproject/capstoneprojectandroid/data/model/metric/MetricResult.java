package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricResult {

    private final MetricMeasurementList mMetricMeasurementList;
    private final MetricGoal mMetricGoal;

    public MetricResult(MetricMeasurementList metricMeasurementList, MetricGoal metricGoal) {
        mMetricMeasurementList = metricMeasurementList;
        mMetricGoal = metricGoal;
    }

    public MetricMeasurementList getMetricMeasurementList() {
        return mMetricMeasurementList;
    }

    public MetricGoal getMetricGoal() {
        return mMetricGoal;
    }

    public boolean getResult() {
        return mMetricGoal.getComparator().compare(getActualValue(), getExpectedValue());
    }

    public float getExpectedValue() {
        return mMetricGoal.getGoal();
    }

    public float getActualValue() {
        // TODO

        return -Float.MAX_VALUE;
    }
}
