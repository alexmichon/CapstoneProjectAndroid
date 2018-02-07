package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;

/**
 * Created by Alex on 18/12/2017.
 */

public class MetricFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static Metric create() {
        return builder()
                .withName("Metric" + ID)
                .withSensor(SensorManager.find(SensorManager.ID_ACCELEROMETER))
                .withMin(0)
                .withMax(1)
                .build();
    }

    public static class Builder {

        private Sensor mSensor;
        private String mName;

        private float mMin;
        private float mMax;

        public Builder withSensor(Sensor sensor) {
            mSensor = sensor;
            return this;
        }

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public Builder withMin(float min) {
            mMin = min;
            return this;
        }

        public Builder withMax(float max) {
            mMax = max;
            return this;
        }

        public Metric build() {
            Metric metric = new Metric(ID++, mName, mSensor);
            metric.setMax(mMax);
            metric.setMin(mMin);

            return metric;
        }
    }
}
