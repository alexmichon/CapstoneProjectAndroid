package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 21/12/2017.
 */

public class ExerciseGoalResponse extends BaseResponse<ExerciseGoalCreator> {

    @Expose
    @SerializedName("id")
    private int mId;

    @Expose
    @SerializedName("metric_goals")
    private List<MetricGoalResponse> mMetricGoalResponses;

    @Override
    public ExerciseGoalCreator get() {
        ExerciseGoalCreator creator = new ExerciseGoalCreator();
        creator.setType(ExerciseGoal.Type.DEFAULT);

        List<MetricGoal> metricGoals = new ArrayList<>();
        for (MetricGoalResponse m: mMetricGoalResponses) {
            metricGoals.add(m.get());
        }
        creator.setMetricGoals(metricGoals);

        return creator;
    }
}
