package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;

/**
 * Created by Alex on 19/11/2017.
 */

public class MetricRequest {

    @SerializedName("name")
    @Expose
    private final String mName;

    @SerializedName("sensor_id")
    @Expose
    private final int mSensorId;

    public MetricRequest(Metric metric) {
        mName = metric.getName();
        mSensorId = metric.getSensor().getId();
    }
}
