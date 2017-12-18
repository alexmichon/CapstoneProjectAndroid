package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

/**
 * Created by Alex on 18/12/2017.
 */

public class MetricGoalFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Metric mMetric;
        private float mGoal;
        private MetricGoal.Type mType;
        private MetricComparator mComparator;

        public Builder withMetric(Metric metric) {
            mMetric = metric;
            return this;
        }

        public Builder withGoal(float goal) {
            mGoal = goal;
            return this;
        }

        public Builder withType(MetricGoal.Type type) {
            mType = type;
            return this;
        }

        public Builder withComparator(MetricComparator comparator) {
            mComparator = comparator;
            return this;
        }

        public MetricGoal build() {
            MetricGoal metricGoal = new MetricGoal(mMetric, mGoal, mType, mComparator);
            return metricGoal;
        }
    }
}
