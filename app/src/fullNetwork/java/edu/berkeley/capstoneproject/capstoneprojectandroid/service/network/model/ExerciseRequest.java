package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 19/11/2017.
 */

public class ExerciseRequest {

    @SerializedName("exercise_type_id")
    @Expose
    private final int mExerciseTypeId;

    public ExerciseRequest(ExerciseType exerciseType) {
        mExerciseTypeId = exerciseType.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ExerciseRequest that = (ExerciseRequest) obj;

        return this.mExerciseTypeId == that.mExerciseTypeId;
    }
}
