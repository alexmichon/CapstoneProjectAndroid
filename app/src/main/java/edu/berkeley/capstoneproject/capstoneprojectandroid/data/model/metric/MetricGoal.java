package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricGoal {

    public enum Type {
        MIN, MAX, MEAN
    }

    private final Metric mMetric;
    private final Type mType;
    private final MetricComparator mComparator;

    private float mGoal;

    public MetricGoal(Metric metric, float goal, Type type, MetricComparator comparator) {
        mMetric = metric;
        mType = type;
        mComparator = comparator;
        mGoal = goal;
    }

    public Metric getMetric() {
        return mMetric;
    }

    public Type getType() {
        return mType;
    }

    public MetricComparator getComparator() {
        return mComparator;
    }

    public float getGoal() {
        return mGoal;
    }

    public void setGoal(float goal) {
        mGoal = goal;
    }
}
