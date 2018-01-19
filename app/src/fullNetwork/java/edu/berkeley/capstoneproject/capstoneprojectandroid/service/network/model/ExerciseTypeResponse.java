package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 21/12/2017.
 */

public class ExerciseTypeResponse {

    @Expose
    @SerializedName("id")
    private int mId;

    @Expose
    @SerializedName("name")
    private String mName;

    @Expose
    @SerializedName("description")
    private String mDescription;

    @Expose
    @SerializedName("video_url")
    private String mVideoUrl;

    public ExerciseType getExerciseType() {
        ExerciseType exerciseType = new ExerciseType(mId, mName, mDescription);
        exerciseType.setYoutubeVideo(mVideoUrl);
        return exerciseType;
    }
}
