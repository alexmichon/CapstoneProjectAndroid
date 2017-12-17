package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;

/**
 * Created by Alex on 09/11/2017.
 */

public class Exercise implements Parcelable {

    public enum State {
        UNSTARTED, STARTED, ENDED
    }

    private final int mId;

    private final ExerciseType mType;
    private Date mStartDate;
    private Date mEndDate;

    private State mState = State.UNSTARTED;

    private final Map<Metric, List<Measurement>> mMeasurements = new HashMap<>();

    public Exercise(int id, ExerciseType type) {
        mId = id;
        mType = type;
    }

    public ExerciseType getType() {
        return mType;
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

    public void addMeasurement(Measurement measurement) {
        if (!mMeasurements.containsKey(measurement.getMetric())) {
            mMeasurements.put(measurement.getMetric(), new ArrayList<Measurement>());
        }

        mMeasurements.get(measurement.getMetric()).add(measurement);
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







    protected Exercise(Parcel in) {
        mId = in.readInt();
        mType = in.readParcelable(ExerciseType.class.getClassLoader());
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
        parcel.writeParcelable(mType, i);
    }
}
