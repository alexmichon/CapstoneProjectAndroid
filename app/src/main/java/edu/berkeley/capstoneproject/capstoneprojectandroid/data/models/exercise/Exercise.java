package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise;

/**
 * Created by Alex on 09/11/2017.
 */

public class Exercise {

    private final ExerciseType mType;

    public Exercise(ExerciseType type) {
        mType = type;
    }

    public ExerciseType getType() {
        return mType;
    }
}
