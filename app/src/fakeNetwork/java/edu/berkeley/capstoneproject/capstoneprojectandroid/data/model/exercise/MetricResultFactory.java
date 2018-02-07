package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 06/02/2018.
 */

public class MetricResultFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static MetricResult create() {
        return builder().build();
    }

    public static List<MetricResult> createList(int n) {
        List<MetricResult> metricResults = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            metricResults.add(create());
        }

        return metricResults;
    }

    public static MetricResult fromMetricGoal(MetricGoal metricGoal) {
        float actual = (float) (Math.random() * (metricGoal.getMetricMax() - metricGoal.getMetricMin()) + metricGoal.getMetricMin());
        return builder()
                .withMetricGoal(metricGoal)
                .withActual(actual)
                .withResult(actual > metricGoal.getGoal())
                .build();
    }

    public static List<MetricResult> fromMetricGoals(List<MetricGoal> metricGoals) {
        List<MetricResult> metricResults = new ArrayList<>(metricGoals.size());
        for (MetricGoal metricGoal: metricGoals) {
            metricResults.add(fromMetricGoal(metricGoal));
        }

        return metricResults;
    }

    public static class Builder {

        private MetricGoal mMetricGoal;

        private float mActual;
        private float mExpected;

        private boolean mResult;

        public Builder withMetricGoal(MetricGoal metricGoal) {
            mMetricGoal = metricGoal;
            mExpected = metricGoal.getGoal();
            return this;
        }

        public Builder withActual(float actual) {
            mActual = actual;
            return this;
        }

        public Builder withExpected(float expected) {
            mExpected = expected;
            return this;
        }

        public Builder withResult(boolean result) {
            mResult = result;
            return this;
        }

        public MetricResult build() {
            return new MetricResult(mMetricGoal, mActual, mExpected, mResult);
        }
    }
}
