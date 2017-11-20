package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

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

public class Exercise {

    public enum State {
        UNSTARTED, STARTED, ENDED
    }

    private int mID;

    private String mName;

    private final ExerciseType mType;
    private Date mStartDate;
    private Date mEndDate;

    private State mState = State.UNSTARTED;

    private final Map<Metric, List<Measurement>> mMeasurements = new HashMap<>();

    public Exercise(ExerciseType type) {
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

    public String getName() {
        if (mName != null) {
            return mName;
        }

        return getDefaultName();
    }

    private String getDefaultName() {
        StringBuilder sb = new StringBuilder();
        if (mType != null) {
            sb.append(mType.getName());
        }
        else {
            sb.append("Exercise");
        }

        if (mStartDate != null) {
            sb.append(" " + mStartDate.toString());
        }

        return sb.toString();
    }

    public void setName(String name) {
        mName = name;
    }

    public void addMeasurement(Measurement measurement) {
        if (!mMeasurements.containsKey(measurement.getMetric())) {
            mMeasurements.put(measurement.getMetric(), new ArrayList<Measurement>());
        }

        mMeasurements.get(measurement.getMetric()).add(measurement);
    }

    public int getId() {
        return mID;
    }

    public void setId(int ID) {
        mID = ID;
    }
}
