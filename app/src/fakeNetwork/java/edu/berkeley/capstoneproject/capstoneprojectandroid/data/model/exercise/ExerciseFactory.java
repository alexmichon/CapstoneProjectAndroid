package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

/**
 * Created by Alex on 21/01/2018.
 */

public class ExerciseFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static Exercise create() {
        return builder()
                .withExerciseType(ExerciseTypeFactory.create())
                .build();
    }

    public static Exercise fromBuilder(Exercise.Builder builder) {
        return new Builder(builder).build();
    }

    public static class Builder {

        private int mExerciseTypeId;
        private int mDuration;

        public Builder() {

        }

        public Builder(Exercise.Builder builder) {
            withExerciseType(builder.getExerciseType());
        }

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseTypeId = exerciseType.getId();
            mDuration = exerciseType.getDuration();
            return this;
        }

        public Builder withExerciseTypeId(int exerciseTypeId) {
            mExerciseTypeId = exerciseTypeId;
            return this;
        }

        public Builder withDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public Exercise build() {
            Exercise exercise = new Exercise(ID++, mExerciseTypeId);
            exercise.setDuration(mDuration);
            return exercise;
        }
    }
}

