package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;

/**
 * Created by Alex on 19/11/2017.
 */

public class ExerciseRequest extends BaseRequest{

    @SerializedName("exercise_type_id")
    @Expose
    int mExerciseTypeId;

    @SerializedName("name")
    @Expose
    String mName;

    public ExerciseRequest(Exercise.Builder builder) {
        mExerciseTypeId = builder.getExerciseTypeId();
        mName = builder.getName();
    }
}
