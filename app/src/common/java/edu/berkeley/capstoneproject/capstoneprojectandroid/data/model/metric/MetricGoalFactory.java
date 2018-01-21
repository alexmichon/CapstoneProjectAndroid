package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

/**
 * Created by Alex on 18/12/2017.
 */

public class MetricGoalFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int mMetricId;
        private float mGoal;
        private String mType;
        private String mComparator;

        public Builder withMetric(Metric metric) {
            mMetricId = metric.getId();
            return this;
        }

        public Builder withMetricId(int metricId) {
            mMetricId = metricId;
            return this;
        }

        public Builder withGoal(float goal) {
            mGoal = goal;
            return this;
        }

        public Builder withType(String type) {
            mType = type;
            return this;
        }

        public Builder withComparator(String comparator) {
            mComparator = comparator;
            return this;
        }

        public MetricGoal build() {
            MetricGoal metricGoal = new MetricGoal(ID++, mMetricId, mGoal, mType, mComparator);
            return metricGoal;
        }
    }
}
