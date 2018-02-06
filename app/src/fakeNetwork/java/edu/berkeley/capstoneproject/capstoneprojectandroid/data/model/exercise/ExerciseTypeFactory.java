package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 20/01/2018.
 */

public class ExerciseTypeFactory {

    private static int ID = 0;

    public static ExerciseType create() {
        return new Builder()
                .withName("Test Exercise Type")
                .withDescription("This is a test exercise type")
                .build();
    }

    public static class Builder {

        private String mName;
        private String mDescription;

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public Builder withDescription(String description) {
            mDescription = description;
            return this;
        }

        public ExerciseType build() {
            ExerciseType exerciseType = new ExerciseType(ID++, mName, mDescription);
            return exerciseType;
        }
    }
}
