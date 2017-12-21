package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

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

    private final int mExerciseId;

    public MeasurementRequest(Measurement measurement) {
        mTookAt = measurement.getTimestamp();
        mValue = measurement.getValue();
        mMetricId = measurement.getMetric().getId();
        mExerciseId = measurement.getExercise().getId();
    }

    public int getExerciseId() {
        return mExerciseId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MeasurementRequest that = (MeasurementRequest) obj;

        return (this.mExerciseId == that.mExerciseId) &&
                (this.mMetricId == that.mMetricId) &&
                (this.mTookAt == that.mTookAt) &&
                (this.mValue == that.mValue);
    }
}
