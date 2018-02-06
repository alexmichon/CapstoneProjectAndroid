package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoal implements Parcelable {


    private final int mId;
    private List<MetricGoal> mMetricGoals;

    public ExerciseGoal(int id, List<MetricGoal> metricGoals) {
        mId = id;
        mMetricGoals = metricGoals;
    }

    public int getId() {
        return mId;
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
            if (m.getMetricId() == metric.getId()) {
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



    protected ExerciseGoal(Parcel in) {
        mId = in.readInt();
        mMetricGoals = new ArrayList<>();
        in.readList(mMetricGoals, null);
    }

    public static final Creator<ExerciseGoal> CREATOR = new Creator<ExerciseGoal>() {
        @Override
        public ExerciseGoal createFromParcel(Parcel in) {
            return new ExerciseGoal(in);
        }

        @Override
        public ExerciseGoal[] newArray(int size) {
            return new ExerciseGoal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeList(mMetricGoals);
    }
}
