package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

/**
 * Created by Alex on 19/12/2017.
 */

public class ExerciseCreator {

    private ExerciseType mExerciseType;
    private ExerciseGoal mExerciseGoal;

    public ExerciseCreator() {

    }

    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        mExerciseType = exerciseType;
    }

    public ExerciseGoal getExerciseGoal() {
        return mExerciseGoal;
    }

    public void setExerciseGoal(ExerciseGoal exerciseGoal) {
        mExerciseGoal = exerciseGoal;
    }
}
