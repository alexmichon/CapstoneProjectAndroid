package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 19/11/2017.
 */

public class ExerciseRequest extends BaseRequest {

    @SerializedName("exercise")
    @Expose
    private final ExerciseNestedRequest mExerciseNestedRequest;

    public ExerciseRequest(Exercise.Builder builder) {
        mExerciseNestedRequest = new ExerciseNestedRequest(builder);
    }


    private static class ExerciseNestedRequest {

        @SerializedName("exercise_type_id")
        @Expose
        private final int mExerciseTypeId;

        public ExerciseNestedRequest(Exercise.Builder builder) {
            mExerciseTypeId = builder.getExerciseTypeId();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            ExerciseNestedRequest that = (ExerciseNestedRequest) obj;

            return this.mExerciseTypeId == that.mExerciseTypeId;
        }
    }
}
