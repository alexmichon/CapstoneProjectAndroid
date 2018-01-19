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
}
