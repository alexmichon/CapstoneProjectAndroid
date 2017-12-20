package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

/**
 * Created by Alex on 19/12/2017.
 */

public class ExerciseCreator {

    private final ExerciseType mExerciseType;
    private ExerciseGoal mExerciseGoal;

    public ExerciseCreator(ExerciseType exerciseType) {
        mExerciseType = exerciseType;
    }

    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    public ExerciseGoal getExerciseGoal() {
        return mExerciseGoal;
    }

    public void setExerciseGoal(ExerciseGoal exerciseGoal) {
        mExerciseGoal = exerciseGoal;
    }
}
