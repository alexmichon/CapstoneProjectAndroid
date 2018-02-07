package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static ExerciseGoal create(Exercise exercise) {
        return builder()
                .withMetricGoals(MetricGoalFactory.createList(5))
                .build();
    }


    public static class Builder {

        private List<MetricGoal> mMetricGoals = new ArrayList<>();

        public Builder addMetricGoal(MetricGoal metricGoal) {
            mMetricGoals.add(metricGoal);
            return this;
        }

        public Builder withMetricGoals(List<MetricGoal> metricGoals) {
            mMetricGoals = metricGoals;
            return this;
        }

        public ExerciseGoal build() {
            ExerciseGoal exerciseGoal = new ExerciseGoal(ID++, mMetricGoals);

            return exerciseGoal;
        }
    }
}
