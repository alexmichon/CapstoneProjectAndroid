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

public class ExerciseResult {

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

    public int getSize() {
        return mMetricResults.size();
    }
}
