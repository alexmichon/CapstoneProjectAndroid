package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }



    public static class Builder {

        private List<MetricGoal> mMetricGoals = new ArrayList<>();

        public Builder addMetricGoal(MetricGoal metricGoal) {
            mMetricGoals.add(metricGoal);
            return this;
        }

        public ExerciseGoal build() {
            ExerciseGoal exerciseGoal = new ExerciseGoal(ID++, mMetricGoals);
            return exerciseGoal;
        }
    }
}
