package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;

/**
 * Created by Alex on 19/11/2017.
 */

public class ExerciseRequest {

    @SerializedName("name")
    @Expose
    private final String mName;

    public ExerciseRequest(Exercise exercise) {
        mName = exercise.getName();
    }
}
