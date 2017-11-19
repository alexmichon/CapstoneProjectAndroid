package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 09/11/2017.
 */

public class ExerciseType implements Parcelable {

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


    public static final Parcelable.Creator CREATOR = new Creator<ExerciseType>() {
        @Override
        public ExerciseType createFromParcel(Parcel parcel) {
            return new ExerciseType(parcel);
        }

        @Override
        public ExerciseType[] newArray(int i) {
            return new ExerciseType[i];
        }
    };


    public int describeContents() {
        return 0;
    }

    public ExerciseType(Parcel parcel) {
        mName = parcel.readString();
        mDescription = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mDescription);
    }
}
