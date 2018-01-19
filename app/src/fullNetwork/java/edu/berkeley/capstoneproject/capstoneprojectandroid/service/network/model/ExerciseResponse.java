package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 19/11/2017.
 */

public class ExerciseResponse {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("exercise_type_id")
    @Expose
    private int mExerciseTypeId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getExerciseTypeId() {
        return mExerciseTypeId;
    }

    public void setExerciseTypeId(int exerciseTypeId) {
        mExerciseTypeId = exerciseTypeId;
    }

    public Exercise getExercise(Exercise.Builder exerciseCreator) {
        if (mExerciseTypeId != exerciseCreator.getExerciseTypeId()) {
            return null;
        }

        return new Exercise(mId, exerciseCreator.getExerciseTypeId());
    }
}
