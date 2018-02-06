package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<MetricResult> mMetricResults = new ArrayList<>();

        public Builder withMetricResults(List<MetricResult> metricResults) {
            mMetricResults = metricResults;
            return this;
        }

        public Builder addMetricResult(MetricResult metricResult) {
            mMetricResults.add(metricResult);
            return this;
        }

        public ExerciseResult build() {
            ExerciseResult exerciseResult = new ExerciseResult(mMetricResults);
            return exerciseResult;
        }
    }
}
