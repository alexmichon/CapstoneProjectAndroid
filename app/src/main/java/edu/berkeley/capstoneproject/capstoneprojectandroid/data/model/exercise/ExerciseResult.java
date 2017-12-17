package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricMeasurementList;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResult implements Parcelable {

    private final Exercise mExercise;
    private final ExerciseGoal mExerciseGoal;

    private final List<MetricResult> mMetricResults;

    public ExerciseResult(Exercise exercise, ExerciseGoal exerciseGoal) {
        mExercise = exercise;
        mExerciseGoal = exerciseGoal;

        mMetricResults = new ArrayList<>();
    }

    public Exercise getExercise() {
        return mExercise;
    }

    public ExerciseGoal getExerciseGoal() {
        return mExerciseGoal;
    }

    public List<MetricResult> getMetricResults() {
        return mMetricResults;
    }

    public MetricResult getMetricResult(int index) {
        return mMetricResults.get(index);
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mExercise, i);
        parcel.writeParcelable(mExerciseGoal, i);
        parcel.writeList(mMetricResults);
    }

    protected ExerciseResult(Parcel in) {
        mExercise = in.readParcelable(Exercise.class.getClassLoader());
        mExerciseGoal = in.readParcelable(ExerciseGoal.class.getClassLoader());

        mMetricResults = new ArrayList<>();
        in.readList(mMetricResults, ArrayList.class.getClassLoader());
    }

    public static final Creator<ExerciseResult> CREATOR = new Creator<ExerciseResult>() {
        @Override
        public ExerciseResult createFromParcel(Parcel in) {
            return new ExerciseResult(in);
        }

        @Override
        public ExerciseResult[] newArray(int size) {
            return new ExerciseResult[size];
        }
    };

    public int getSize() {
        return mMetricResults.size();
    }
}
