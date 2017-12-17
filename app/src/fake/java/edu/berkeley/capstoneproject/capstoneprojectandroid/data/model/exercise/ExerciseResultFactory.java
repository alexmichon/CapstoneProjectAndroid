package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Exercise mExercise;
        private ExerciseGoal mExerciseGoal;

        public ExerciseResult build() {
            ExerciseResult exerciseResult = new ExerciseResult(mExercise, mExerciseGoal);
            return exerciseResult;
        }
    }
}
