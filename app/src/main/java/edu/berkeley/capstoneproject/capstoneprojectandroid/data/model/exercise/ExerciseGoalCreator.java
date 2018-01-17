package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 19/12/2017.
 */

public class ExerciseGoalCreator {

    private List<MetricGoal> mMetricGoals;

    public ExerciseGoalCreator() {
        mMetricGoals = new ArrayList<>();
    }

    public List<MetricGoal> getMetricGoals() {
        return mMetricGoals;
    }

    public void setMetricGoals(List<MetricGoal> metricGoals) {
        mMetricGoals = metricGoals;
    }
}
