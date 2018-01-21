package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricGoal implements Parcelable {

    private final int mId;
    private int mMetricId;
    private String mMetricName;
    private String mAggregator;
    private String mComparator;
    private float mGoal;
    private String mUnit;
    private float mMetricMax;
    private float mMetricMin;

    public MetricGoal(int id, int metricId, float goal, String type, String comparator) {
        mId = id;
        mMetricId = metricId;
        mAggregator = type;
        mComparator = comparator;
        mGoal = goal;
    }

    public int getId() {
        return mId;
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

    public float getMetricMin() {
        return mMetricMin;
    }

    public void setMetricMin(float metricMin) {
        mMetricMin = metricMin;
    }

    public float getMetricMax() {
        return mMetricMax;
    }

    public void setMetricMax(float metricMax) {
        mMetricMax = metricMax;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }








    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeInt(mMetricId);
        parcel.writeString(mMetricName);
        parcel.writeString(mAggregator);
        parcel.writeString(mComparator);
        parcel.writeFloat(mGoal);
    }

    protected MetricGoal(Parcel in) {
        mId = in.readInt();
        mMetricId = in.readInt();
        mMetricName = in.readString();
        mAggregator = in.readString();
        mComparator = in.readString();
        mGoal = in.readFloat();
    }

    public static final Creator<MetricGoal> CREATOR = new Creator<MetricGoal>() {
        @Override
        public MetricGoal createFromParcel(Parcel in) {
            return new MetricGoal(in);
        }

        @Override
        public MetricGoal[] newArray(int size) {
            return new MetricGoal[size];
        }
    };
}