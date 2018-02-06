package edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;

/**
 * Created by Alex on 21/01/2018.
 */

public class MeasurementFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Exercise mExercise;
        private Metric mMetric;
        private long mTimestamp;
        private float mValue;

        public Builder withExercise(Exercise exercise) {
            mExercise = exercise;
            return this;
        }

        public Builder withMetric(Metric metric) {
            mMetric = metric;
            return this;
        }

        public Builder withTimestamp(long timestamp) {
            mTimestamp = timestamp;
            return this;
        }

        public Builder withValue(float value) {
            mValue = value;
            return this;
        }

        public Measurement build() {
            Measurement measurement = new Measurement(mExercise, mMetric, mTimestamp, mValue);
            return measurement;
        }
    }
}
