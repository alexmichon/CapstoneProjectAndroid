package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 22/12/2017.
 */

public class ExerciseResultResponse extends BaseResponse<ExerciseResult> {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("metric_results")
    @Expose
    private List<MetricResultResponse> mMetricResultResponses;

    @Override
    public ExerciseResult get() {
        List<MetricResult> metricResults = new ArrayList<>();
        for (MetricResultResponse metricResultResponse: mMetricResultResponses) {
            metricResults.add(metricResultResponse.get());
        }

        return new ExerciseResult(metricResults);
    }
}
