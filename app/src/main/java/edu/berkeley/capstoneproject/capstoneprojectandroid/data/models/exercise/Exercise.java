package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise;

import java.util.Date;

/**
 * Created by Alex on 09/11/2017.
 */

public class Exercise {

    public enum State {
        UNSTARTED, STARTED, ENDED
    }

    private final ExerciseType mType;
    private Date mStartDate;
    private Date mEndDate;

    private State mState;

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
}
