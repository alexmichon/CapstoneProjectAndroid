package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoal implements Parcelable {

    private final Exercise mExercise;
    private Map<Metric, Float> mMetricsGoals;

    public ExerciseGoal(Exercise exercise) {
        mExercise = exercise;
        mMetricsGoals = new HashMap<>();

        Map<Metric, List<Measurement>> measurements = exercise.getMeasurements();
        for (Metric m: measurements.keySet()) {
            List<Measurement> list = measurements.get(m);
            Measurement max = list.get(0);
            for (Measurement measurement: list) {
                if (measurement.getValue() > max.getValue()) {
                    max = measurement;
                }
            }
            mMetricsGoals.put(m, max.getValue());
        }
    }

    public float getGoal(Metric m) {
        return mMetricsGoals.get(m);
    }

    public Metric getMetric(int index) {
        return new ArrayList<>(mMetricsGoals.keySet()).get(index);
    }

    public Map<Metric, Float> getGoals() {
        return mMetricsGoals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mExercise, PARCELABLE_WRITE_RETURN_VALUE);
        parcel.writeMap(mMetricsGoals);
    }

    protected ExerciseGoal(Parcel in) {
        mExercise = in.readParcelable(Exercise.class.getClassLoader());
        in.readMap(mMetricsGoals, HashMap.class.getClassLoader());
    }

    public static final Creator<ExerciseGoal> CREATOR = new Creator<ExerciseGoal>() {
        @Override
        public ExerciseGoal createFromParcel(Parcel in) {
            return new ExerciseGoal(in);
        }

        @Override
        public ExerciseGoal[] newArray(int size) {
            return new ExerciseGoal[size];
        }
    };
}
