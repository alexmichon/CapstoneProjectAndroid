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

        private ExerciseGoal.Type mType;
        private List<MetricGoal> mMetricGoals = new ArrayList<>();

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
            return exerciseGoal;
        }

        public ExerciseGoalCreator creator() {
            ExerciseGoalCreator creator = new ExerciseGoalCreator();
            creator.setMetricGoals(mMetricGoals);
            creator.setType(mType);

            return creator;
        }
    }
}
