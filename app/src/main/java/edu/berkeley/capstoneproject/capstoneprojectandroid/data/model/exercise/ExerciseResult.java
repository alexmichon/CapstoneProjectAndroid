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

    private final List<MetricResult> mMetricResults;

    public ExerciseResult(List<MetricResult> metricResults) {
        mMetricResults = metricResults;
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





    protected ExerciseResult(Parcel in) {
        mMetricResults = new ArrayList<>();
        in.readList(mMetricResults, null);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(mMetricResults);
    }
}
