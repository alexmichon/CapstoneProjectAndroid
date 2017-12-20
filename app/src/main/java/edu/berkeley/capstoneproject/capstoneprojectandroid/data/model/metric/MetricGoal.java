package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricGoal implements Cloneable {

    public enum Type {
        MIN("Min"), MAX("Max"), MEAN("Mean");

        private final String mName;

        Type(String name) {
            mName = name;
        }

        String getName() {
            return mName;
        }

        static Type find(String name) {
            for (Type t: values()) {
                if (t.getName().equals(name)) {
                    return t;
                }
            }
            return null;
        }
    }

    private Metric mMetric;
    private Type mType;
    private MetricComparator mComparator;
    private float mGoal;

    public MetricGoal(Metric metric, float goal, Type type, MetricComparator comparator) {
        mMetric = metric;
        mType = type;
        mComparator = comparator;
        mGoal = goal;
    }

    public Metric getMetric() {
        return mMetric;
    }

    public void setMetric(Metric metric) {
        mMetric = metric;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
    }

    public MetricComparator getComparator() {
        return mComparator;
    }

    public void setComparator(MetricComparator comparator) {
        mComparator = comparator;
    }

    public float getGoal() {
        return mGoal;
    }

    public void setGoal(float goal) {
        mGoal = goal;
    }

    public MetricGoal clone() throws CloneNotSupportedException {
        MetricGoal metricGoal = (MetricGoal) super.clone();
        metricGoal.setMetric(mMetric);
        metricGoal.setGoal(mGoal);
        metricGoal.setType(mType);
        metricGoal.setComparator(mComparator);

        return metricGoal;
    }
}
