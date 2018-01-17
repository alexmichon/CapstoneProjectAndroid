package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricMeasurementList;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseResponse;

/**
 * Created by Alex on 09/11/2017.
 */

public class Exercise implements Parcelable {

    public enum State {
        UNSTARTED, STARTED, ENDED;
    }

    private final int mId;
    private String mName;

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

    public Exercise(int id, int exerciseTypeId, String name) {
        mId = id;
        mExerciseTypeId = exerciseTypeId;
        mName = name;
    }

    public Exercise(int id, ExerciseType exerciseType, String name) {
        mId = id;
        mExerciseTypeId = exerciseType.getId();
        mName = name;
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
        if (metricMeasurementList == null) {
            metricMeasurementList = new MetricMeasurementList(measurement.getMetric());
            mMetricMeasurementLists.add(metricMeasurementList);
        }
        metricMeasurementList.addMeasurement(measurement);
    }

    public List<MetricMeasurementList> getMetricMeasurementLists() {
        return mMetricMeasurementLists;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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
        private String mName;

        private int mExerciseTypeId;

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseTypeId = exerciseType.getId();
            return this;
        }

        public Builder withExerciseTypeId(int exerciseTypeId) {
            mExerciseTypeId = exerciseTypeId;
            return this;
        }

        public int getExerciseTypeId() {
            return mExerciseTypeId;
        }

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public String getName() {
            return mName;
        }

        public Exercise build() {
            Exercise exercise = new Exercise(mId, mExerciseTypeId, mName);
            return exercise;
        }
    }
}
