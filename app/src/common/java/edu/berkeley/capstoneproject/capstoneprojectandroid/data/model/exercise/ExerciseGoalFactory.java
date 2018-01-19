package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricComparator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoalFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalFactory {

    private static int ID = 0;

    private static Map<ExerciseType, ExerciseGoal> mDefaults = new HashMap<>();

    public static Builder builder() {
        return new Builder();
    }

    public static ExerciseGoal def(ExerciseType exerciseType) {
        if (!mDefaults.containsKey(exerciseType)) {
            mDefaults.put(exerciseType, new ExerciseGoal(ID++, Arrays.asList(
                    MetricGoalFactory.builder()
                            .withMetric(SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(Accelerometer.ID_ACC_X))
                            .withGoal(1.0f)
                            .withType("max")
                            .withComparator(">=")
                            .build())
            ));
        }

        return mDefaults.get(exerciseType);
    }


    public static class Builder {

        private ExerciseType mExerciseType;
        private List<MetricGoal> mMetricGoals = new ArrayList<>();

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseType = exerciseType;
            return this;
        }

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

        public ExerciseGoalCreator creator() {
            ExerciseGoalCreator creator = new ExerciseGoalCreator();
            creator.setMetricGoals(mMetricGoals);

            return creator;
        }
    }
}
