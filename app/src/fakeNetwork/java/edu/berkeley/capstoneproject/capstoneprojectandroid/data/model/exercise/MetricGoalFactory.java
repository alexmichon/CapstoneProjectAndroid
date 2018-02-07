package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 18/12/2017.
 */

public class MetricGoalFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static MetricGoal create() {
        Metric metric = MetricFactory.create();
        return builder()
                .withMetric(metric)
                .withRandomGoal()
                .withComparator(">")
                .withAggregator("max")
                .build();
    }

    public static List<MetricGoal> createList(int n) {
        List<MetricGoal> metricGoals = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            metricGoals.add(create());
        }

        return metricGoals;
    }

    public static class Builder {

        private int mMetricId;
        private float mGoal;

        private String mAggregator;
        private String mComparator;

        private float mMetricMin;
        private float mMetricMax;

        public Builder withMetric(Metric metric) {
            mMetricId = metric.getId();
            mMetricMax = metric.getMax();
            mMetricMin = metric.getMin();
            return this;
        }

        public Builder withMetricId(int metricId) {
            mMetricId = metricId;
            return this;
        }

        public Builder withMetricMin(float metricMin) {
            mMetricMin = metricMin;
            return this;
        }

        public Builder withMetricMax(float metricMax) {
            mMetricMax = metricMax;
            return this;
        }

        public Builder withGoal(float goal) {
            mGoal = goal;
            return this;
        }

        public Builder withRandomGoal() {
            mGoal = (float) (Math.random() * (mMetricMax - mMetricMin) + mMetricMin);
            return this;
        }


        public Builder withAggregator(String aggregator) {
            mAggregator = aggregator;
            return this;
        }

        public Builder withComparator(String comparator) {
            mComparator = comparator;
            return this;
        }

        public MetricGoal build() {
            MetricGoal metricGoal = new MetricGoal(ID++, mMetricId, mGoal, mAggregator, mComparator);
            return metricGoal;
        }
    }
}
