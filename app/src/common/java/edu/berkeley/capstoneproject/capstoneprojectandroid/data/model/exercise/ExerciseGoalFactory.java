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
            mDefaults.put(exerciseType, new ExerciseGoal(ID++, ExerciseGoal.Type.DEFAULT, Arrays.asList(
                    MetricGoalFactory.builder()
                            .withMetric(SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(Accelerometer.ID_ACC_X))
                            .withGoal(1.0f)
                            .withType(MetricGoal.Type.MAX)
                            .withComparator(new MetricComparator(MetricComparator.Type.GT))
                            .build())
            ));
        }

        return mDefaults.get(exerciseType);
    }


    public static class Builder {

        private ExerciseType mExerciseType;
        private ExerciseGoal.Type mType;
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

        public Builder withType(ExerciseGoal.Type type) {
            mType = type;
            return this;
        }

        public ExerciseGoal build() {
            ExerciseGoal exerciseGoal = new ExerciseGoal(ID++, mType, mMetricGoals);

            switch (mType) {
                case DEFAULT:
                    exerciseGoal.setMetricGoals(def(mExerciseType).getMetricGoals());
                    break;
                case CUSTOM:
                    break;
                case NONE:
                    return null;
            }

            return exerciseGoal;
        }

        public ExerciseGoalCreator creator() {
            ExerciseGoalCreator creator = new ExerciseGoalCreator();
            creator.setType(mType);
            creator.setMetricGoals(mMetricGoals);

            return creator;
        }
    }
}
