package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Sensor;

/**
 * Created by Alex on 18/12/2017.
 */

public class MetricFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Sensor mSensor;
        private String mName;

        public Builder withSensor(Sensor sensor) {
            mSensor = sensor;
            return this;
        }

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public Metric build() {
            Metric metric = new Metric(mSensor, ID++, mName);
            return metric;
        }
    }
}
