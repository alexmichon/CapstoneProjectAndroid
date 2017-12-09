package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 09/12/2017.
 */

public class ExerciseTypeFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
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
            return new ExerciseType(ID++, mName, mDescription);
        }
    }
}
