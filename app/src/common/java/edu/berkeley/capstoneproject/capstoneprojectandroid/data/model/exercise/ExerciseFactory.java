package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

/**
 * Created by Alex on 20/12/2017.
 */

public class ExerciseFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int mExerciseTypeId;

        public Builder() {

        }

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseTypeId = exerciseType.getId();
            return this;
        }

        public Builder withExerciseTypeId(int exerciseTypeId) {
            mExerciseTypeId = exerciseTypeId;
            return this;
        }

        public Exercise build() {
            Exercise exercise = new Exercise(ID++, mExerciseTypeId);
            return exercise;
        }

    }
}
