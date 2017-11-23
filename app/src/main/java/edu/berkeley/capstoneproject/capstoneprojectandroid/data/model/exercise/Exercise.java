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

    public int getId() {
        return mId;
    }
}
