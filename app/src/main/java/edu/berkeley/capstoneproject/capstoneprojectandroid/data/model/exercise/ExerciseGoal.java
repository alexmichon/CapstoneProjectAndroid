package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoal {

    public enum Type {
        NONE("None"), DEFAULT("Default"), CUSTOM("Custom");

        private final String mName;

        Type(String name) {
            mName = name;
        }

        public static List<String> nameList() {
            List<String> names = new ArrayList<>();
            for (Type t: values()) {
                names.add(t.getName());
            }

            return names;
        }

        public static Type find(String name) {
            for (Type t: values()) {
                if (t.getName().equals(name)) {
                    return t;
                }
            }

            return null;
        }

        public String getName() {
            return mName;
        }
    }

    private final int mId;
    private Type mType;
    private List<MetricGoal> mMetricGoals;

    public ExerciseGoal(int id, Type type, List<MetricGoal> metricGoals) {
        mId = id;
        mType = type;
        mMetricGoals = metricGoals;
    }

    public int getId() {
        return mId;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
    }

    public List<MetricGoal> getMetricGoals() {
        return mMetricGoals;
    }

    public void setMetricGoals(List<MetricGoal> metricGoals) {
        mMetricGoals = metricGoals;
    }

    public MetricGoal getMetricGoal(int index) {
        return mMetricGoals.get(index);
    }

    public MetricGoal getMetricGoal(Metric metric) {
        for (MetricGoal m: mMetricGoals) {
            if (m.getMetric() == metric) {
                return m;
            }
        }

        return null;
    }

    public void addMetricGoal(MetricGoal metricGoal) {
        mMetricGoals.add(metricGoal);
    }

    public void removeMetricGoal(MetricGoal metricGoal) {
        mMetricGoals.remove(metricGoal);
    }
}
