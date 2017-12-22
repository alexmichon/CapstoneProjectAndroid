package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;

/**
 * Created by Alex on 22/12/2017.
 */

public class ExerciseResultResponse extends BaseResponse<ExerciseResult> {

    @Expose
    @SerializedName("id")
    private int mId;

    @Expose
    @SerializedName("exercise_id")
    private int mExerciseId;

    @Override
    public ExerciseResult get() {
        return null;
    }
}
