package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 19/12/2017.
 */

public class ExerciseGoalCreator {

    private List<MetricGoal> mMetricGoals;
    private ExerciseGoal.Type mType;

    public ExerciseGoalCreator() {
        mType = ExerciseGoal.Type.DEFAULT;
        mMetricGoals = new ArrayList<>();
    }

    public List<MetricGoal> getMetricGoals() {
        return mMetricGoals;
    }

    public void setMetricGoals(List<MetricGoal> metricGoals) {
        mMetricGoals = metricGoals;
    }

    public ExerciseGoal.Type getType() {
        return mType;
    }

    public void setType(ExerciseGoal.Type type) {
        mType = type;
    }
}
