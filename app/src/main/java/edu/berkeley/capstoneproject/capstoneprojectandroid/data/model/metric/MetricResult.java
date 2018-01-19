package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricResult {

    private final MetricGoal mMetricGoal;
    private final float mActual;
    private final float mExpected;
    private final boolean mResult;

    public MetricResult(MetricGoal metricGoal, float actual, float expected, boolean result) {
        mMetricGoal = metricGoal;
        mActual = actual;
        mExpected = expected;
        mResult = result;
    }

    public MetricGoal getMetricGoal() {
        return mMetricGoal;
    }

    public String getMetricName() {
        return mMetricGoal.getMetricName();
    }

    public boolean getResult() {
        return mResult;
    }

    public float getActual() {
        return mActual;
    }

    public float getExpected() {
        return mExpected;
    }
}
