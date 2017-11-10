package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise;

/**
 * Created by Alex on 09/11/2017.
 */

public class ExerciseType {

    private String mName;
    private String mDescription;

    public ExerciseType(String name) {
        mName = name;
    }

    public ExerciseType(String name, String description) {
        mName = name;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public String toString() {
        return mName;
    }
}
