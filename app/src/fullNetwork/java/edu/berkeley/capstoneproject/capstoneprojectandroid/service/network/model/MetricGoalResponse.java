package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 24/12/2017.
 */

public class MetricGoalResponse extends BaseResponse<MetricGoal> {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("metric_id")
    @Expose
    private int mMetricId;

    @SerializedName("metric_name")
    @Expose
    private String mMetricName;

    @SerializedName("metric_min")
    @Expose
    private float mMetricMin;

    @SerializedName("metric_max")
    @Expose
    private float mMetricMax;

    @SerializedName("goal")
    @Expose
    private float mGoal;

    @SerializedName("unit")
    @Expose
    private String mUnit;

    @SerializedName("aggregator")
    @Expose
    private String mAggregator;

    @SerializedName("comparator")
    @Expose
    private String mComparator;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getMetricId() {
        return mMetricId;
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

    public float getGoal() {
        return mGoal;
    }

    public void setGoal(float goal) {
        mGoal = goal;
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

    @Override
    public MetricGoal get() {
        MetricGoal metricGoal = new MetricGoal(mId, mMetricId, mGoal, mAggregator, mComparator);
        metricGoal.setMetricName(mMetricName);
        metricGoal.setMetricMin(mMetricMin);
        metricGoal.setMetricMax(mMetricMax);
        metricGoal.setUnit(mUnit);
        return metricGoal;
    }
}
