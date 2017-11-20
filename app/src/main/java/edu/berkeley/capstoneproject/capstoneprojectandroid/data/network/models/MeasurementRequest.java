package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;

/**
 * Created by Alex on 19/11/2017.
 */

public class MeasurementRequest {

    @SerializedName("took_at")
    @Expose
    private final long mTookAt;

    @SerializedName("value")
    @Expose
    private final float mValue;

    @SerializedName("metric_id")
    @Expose
    private final int mMetricId;

    public MeasurementRequest(Measurement measurement) {
        mTookAt = measurement.getTimestamp();
        mValue = measurement.getValue();
        mMetricId = measurement.getMetric().getId();
    }
}
