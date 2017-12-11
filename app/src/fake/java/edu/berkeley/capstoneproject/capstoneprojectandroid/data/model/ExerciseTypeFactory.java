package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model;

import android.net.Uri;

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
        private Uri mVideoUri;
        private String mYoutubeId;

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public Builder withDescription(String description) {
            mDescription = description;
            return this;
        }

        public Builder withVideo(Uri videoUri) {
            mVideoUri = videoUri;
            return this;
        }

        public Builder withYoutubeVideo(String videoId) {
            mYoutubeId = videoId;
            return this;
        }

        public ExerciseType build() {
            ExerciseType exerciseType = new ExerciseType(ID++, mName, mDescription);
            exerciseType.setVideoUri(mVideoUri);
            exerciseType.setYoutubeVideo(mYoutubeId);

            return exerciseType;
        }
    }
}
