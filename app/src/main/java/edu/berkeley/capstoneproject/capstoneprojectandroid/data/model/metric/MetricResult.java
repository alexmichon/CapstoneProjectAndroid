package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricResult {

    private final MetricGoal mMetricGoal;
    private final float mActual;
    private final boolean mResult;

    private String mComment;

    public MetricResult(MetricGoal metricGoal, float actual, boolean result) {
        mMetricGoal = metricGoal;
        mActual = actual;
        mResult = result;
        createComment();
    }

    public MetricResult(MetricGoal metricGoal, float actual, boolean result, String comment) {
        mMetricGoal = metricGoal;
        mActual = actual;
        mResult = result;
        mComment = comment;
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

    public float getExpectedValue() {
        return mMetricGoal.getGoal();
    }

    public float getActualValue() {
        return mActual;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    protected void createComment() {
        if (mResult) {
            mComment = "Good job !";
        }
        else {
            mComment = "You'll do better next time";
        }
    }
}
