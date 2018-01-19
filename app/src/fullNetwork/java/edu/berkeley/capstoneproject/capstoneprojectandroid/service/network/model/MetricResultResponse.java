package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 17/01/2018.
 */

public class MetricResultResponse extends BaseResponse<MetricResult> {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("metric_goal")
    @Expose
    private MetricGoalResponse mMetricGoalResponse;

    @SerializedName("metric_id")
    @Expose
    private int mMetricId;

    @SerializedName("exercise_result_id")
    @Expose
    private int mExerciseResultId;

    @SerializedName("metric_name")
    @Expose
    private String mMetricName;

    @SerializedName("actual")
    @Expose
    private float mActual;

    @SerializedName("expected")
    @Expose
    private float mExpected;

    @SerializedName("result")
    @Expose
    private boolean mResult;


    @Override
    public MetricResult get() {
        MetricGoal metricGoal = mMetricGoalResponse.get();
        return new MetricResult(metricGoal, mActual, mExpected, mResult);
    }
}
