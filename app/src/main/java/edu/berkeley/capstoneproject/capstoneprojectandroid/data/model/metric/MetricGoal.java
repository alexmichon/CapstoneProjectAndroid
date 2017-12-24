package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricGoal implements Cloneable {

    private int mMetricId;
    private String mMetricName;
    private String mAggregator;
    private String mComparator;
    private float mGoal;

    public MetricGoal(int metricId, float goal, String type, String comparator) {
        mMetricId = metricId;
        mAggregator = type;
        mComparator = comparator;
        mGoal = goal;
    }

    public int getMetricId() {
        return mMetricId;
    }

    public void setMetric(Metric metric) {
        mMetricId = metric.getId();
    }

    public void setMetricId(int metricId) {
        mMetricId = metricId;
    }

    public String getMetricName() {
        return mMetricName;
    }

    public void setMetricName(String metricName) {
        mMetricName = metricName;
    }

    public String getAggregator() {
        return mAggregator;
    }

    public void setAggregator(String aggregator) {
        mAggregator = aggregator;
    }

    public String getComparator() {
        return mComparator;
    }

    public void setComparator(String comparator) {
        mComparator = comparator;
    }

    public float getGoal() {
        return mGoal;
    }

    public void setGoal(float goal) {
        mGoal = goal;
    }

    public MetricGoal clone() throws CloneNotSupportedException {
        MetricGoal metricGoal = (MetricGoal) super.clone();
        metricGoal.setMetricId(mMetricId);
        metricGoal.setGoal(mGoal);
        metricGoal.setAggregator(mAggregator);
        metricGoal.setComparator(mComparator);

        return metricGoal;
    }
}