package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoal {

    private Map<Metric, Float> mMetricsGoals;

    public ExerciseGoal() {

    }

    public ExerciseGoal(Map<Metric, Float> metricsGoals) {
        mMetricsGoals = metricsGoals;
    }

    public ExerciseGoal(Exercise exercise) {
        mMetricsGoals = new HashMap<>();

        Map<Metric, List<Measurement>> measurements = exercise.getMeasurements();
        for (Metric m: measurements.keySet()) {
            List<Measurement> list = measurements.get(m);
            Measurement max = list.get(0);
            for (Measurement measurement: list) {
                if (measurement.getValue() > max.getValue()) {
                    max = measurement;
                }
            }
            mMetricsGoals.put(m, max.getValue());
        }
    }

    public float getGoal(Metric m) {
        return mMetricsGoals.get(m);
    }

    public Map<Metric, Float> getGoals() {
        return mMetricsGoals;
    }
}
