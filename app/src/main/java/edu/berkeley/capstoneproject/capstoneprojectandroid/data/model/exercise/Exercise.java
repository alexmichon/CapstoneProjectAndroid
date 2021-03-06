package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
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

    private ExerciseGoal mExerciseGoal;
    private ExerciseType mExerciseType;

    private String mName;
    private String mExerciseTypeName;

    private Date mStartDate;
    private Date mEndDate;

    private int mDuration;

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

    public List<MetricMeasurementList> getMetricMeasurementLists() {
        return mMetricMeasurementLists;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date endDate) {
        mEndDate = endDate;
    }

    public int getId() {
        return mId;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        mExerciseType = exerciseType;
    }

    public String getExerciseTypeName() {
        return mExerciseTypeName;
    }

    public void setExerciseTypeName(String exerciseTypeName) {
        mExerciseTypeName = exerciseTypeName;
    }

    public ExerciseGoal getExerciseGoal() {
        return mExerciseGoal;
    }

    public void setExerciseGoal(ExerciseGoal exerciseGoal) {
        mExerciseGoal = exerciseGoal;
    }




    protected Exercise(Parcel in) {
        mId = in.readInt();
        mExerciseTypeId = in.readInt();
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

        private ExerciseType mExerciseType;

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseType = exerciseType;
            return this;
        }

        public ExerciseType getExerciseType() {
            return mExerciseType;
        }

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public String getName() {
            return mName;
        }

        public Exercise build() {
            Exercise exercise = new Exercise(mId, mExerciseType, mName);
            return exercise;
        }
    }
}
