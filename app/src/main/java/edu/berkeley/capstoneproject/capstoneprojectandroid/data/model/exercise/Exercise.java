package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricMeasurementList;

/**
 * Created by Alex on 09/11/2017.
 */

public class Exercise implements Parcelable {

    public enum State {
        UNSTARTED, STARTED, ENDED;
    }

    private final int mId;

    private final int mExerciseTypeId;

    private Date mStartDate;
    private Date mEndDate;
    private State mState = State.UNSTARTED;

    private final List<MetricMeasurementList> mMetricMeasurementLists = new ArrayList<>();

    public Exercise(int id, int exerciseTypeId) {
        mId = id;
        mExerciseTypeId = exerciseTypeId;
    }

    public Exercise(int id, ExerciseType exerciseType) {
        mId = id;
        mExerciseTypeId = exerciseType.getId();
    }

    public int getExerciseTypeId() {
        return mExerciseTypeId;
    }

    public void start() {
        mState = State.STARTED;
        mStartDate = new Date();
    }

    public void stop() {
        mState = State.ENDED;
        mEndDate = new Date();
    }

    public State getState() {
        return mState;
    }

    public MetricMeasurementList getMetricMeasurementList(Metric metric) {
        for (MetricMeasurementList m: mMetricMeasurementLists) {
            if (m.getMetric() == metric) {
                return m;
            }
        }

        return null;
    }

    public void addMeasurement(Measurement measurement) {
        MetricMeasurementList metricMeasurementList = getMetricMeasurementList(measurement.getMetric());
        if (metricMeasurementList != null) {
            metricMeasurementList.addMeasurement(measurement);
        }
    }

    public List<MetricMeasurementList> getMetricMeasurementLists() {
        return mMetricMeasurementLists;
    }

    public int getId() {
        return mId;
    }




    protected Exercise(Parcel in) {
        mId = in.readInt();
        mExerciseTypeId = in.readParcelable(ExerciseType.class.getClassLoader());
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeInt(mExerciseTypeId);
    }




    public static class Builder {
        private int mId;

        private int mExerciseTypeId;

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseTypeId = exerciseType.getId();
            return this;
        }

        public Builder withExerciseTypeId(int exerciseTypeId) {
            mExerciseTypeId = exerciseTypeId;
            return this;
        }

        public Exercise build() {
            Exercise exercise = new Exercise(mId, mExerciseTypeId);
            return exercise;
        }
    }
}
