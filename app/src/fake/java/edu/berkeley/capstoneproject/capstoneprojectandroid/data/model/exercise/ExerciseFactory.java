package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import dagger.Component;

/**
 * Created by Alex on 20/12/2017.
 */

public class ExerciseFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ExerciseType mExerciseType;
        private ExerciseGoal mExerciseGoal;

        public Builder() {

        }

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseType = exerciseType;
            return this;
        }

        public Builder withExerciseGoal(ExerciseGoal exerciseGoal) {
            mExerciseGoal = exerciseGoal;
            return this;
        }

        public Exercise build() {
            Exercise exercise = new Exercise(ID++, mExerciseType);
            exercise.setExerciseGoal(mExerciseGoal);

            return exercise;
        }

    }
}
