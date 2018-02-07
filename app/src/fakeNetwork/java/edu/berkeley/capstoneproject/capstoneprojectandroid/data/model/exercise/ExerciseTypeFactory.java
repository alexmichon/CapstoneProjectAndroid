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
                .withDuration(10)
                .withVideoUrl("Iu1qa8N2ID0")
                .build();
    }

    public static class Builder {

        private String mName;
        private String mDescription;

        private int mDuration;

        private String mVideoUrl;

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public Builder withDescription(String description) {
            mDescription = description;
            return this;
        }

        public Builder withDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public Builder withVideoUrl(String videoUrl) {
            mVideoUrl = videoUrl;
            return this;
        }

        public ExerciseType build() {
            ExerciseType exerciseType = new ExerciseType(ID++, mName, mDescription);

            exerciseType.setDuration(mDuration);
            exerciseType.setYoutubeVideo(mVideoUrl);

            return exerciseType;
        }
    }
}
