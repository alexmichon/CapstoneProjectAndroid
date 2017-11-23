package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 09/11/2017.
 */

public class ExerciseType implements Parcelable {

    private final int mId;

    private final String mName;
    private final String mDescription;

    public ExerciseType(int id, String name, String description) {
        mId = id;
        mName = name;
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
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
        mId = parcel.readInt();
        mName = parcel.readString();
        mDescription = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
    }
}
