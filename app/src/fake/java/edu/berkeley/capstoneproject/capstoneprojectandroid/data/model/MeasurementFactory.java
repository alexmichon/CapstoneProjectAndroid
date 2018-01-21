package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;

/**
 * Created by Alex on 07/12/2017.
 */

public class MeasurementFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static ListBuilder listBuilder(int count) {
        return new ListBuilder(count);
    }

    public static class Builder {

        private Metric mMetric;
        private long mTimestamp;
        private float mValue;

        public Builder() {
            mTimestamp = new Random().nextLong();
            mValue = new Random().nextFloat();
        }

        public Builder withMetric(Metric metric) {
            mMetric = metric;
            return this;
        }

        public Builder withTimestamp(long timestamp) {
            mTimestamp = timestamp;
            return this;
        }

        public Builder withinRange(float min, float max) {
            mValue = new Random().nextFloat() * (max - min) + min;
            return this;
        }

        public Measurement build() {
            return new Measurement(mMetric, mTimestamp, mValue);
        }

    }

    public static class ListBuilder {
        private int mCount;

        private Metric mMetric;
        private long mSampleRate;

        private float mValueMin;
        private float mValueMax;

        public ListBuilder(int count) {
            mCount = count;
            mSampleRate = new Random().nextLong();
        }

        public ListBuilder withMetric(Metric metric) {
            mMetric = metric;
            return this;
        }

        public ListBuilder withSampleRate(long sampleRate) {
            mSampleRate = sampleRate;
            return this;
        }

        public ListBuilder withinRange(float min, float max) {
            mValueMin = min;
            mValueMax = max;
            return this;
        }

        public List<Measurement> build() {
            List<Measurement> measurements = new ArrayList<>(mCount);
            for (int i = 0; i < mCount; i++) {
                measurements.add(new Builder()
                        .withMetric(mMetric)
                        .withTimestamp(i * mSampleRate)
                        .withinRange(mValueMin, mValueMax)
                        .build()
                );
            }

            return measurements;
        }
    }

}
