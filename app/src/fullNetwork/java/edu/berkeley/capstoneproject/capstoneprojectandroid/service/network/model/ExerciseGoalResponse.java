package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 21/12/2017.
 */

public class ExerciseGoalResponse {

    @Expose
    @SerializedName("id")
    private int mId;

    @Expose
    @SerializedName("metric_goals")
    private List<MetricGoal> mMetricGoals;

    public ExerciseGoal toExerciseGoal() {
        return new ExerciseGoal(mId, ExerciseGoal.Type.DEFAULT, mMetricGoals);
    }

    public ExerciseGoalCreator toCreator() {
        ExerciseGoalCreator creator = new ExerciseGoalCreator();
        creator.setType(ExerciseGoal.Type.DEFAULT);
        creator.setMetricGoals(mMetricGoals);
        return creator;
    }
}
