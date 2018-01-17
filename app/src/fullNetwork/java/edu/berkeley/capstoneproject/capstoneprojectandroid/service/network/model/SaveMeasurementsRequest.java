package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricMeasurementList;

/**
 * Created by Alex on 14/01/2018.
 */

public class SaveMeasurementsRequest extends ListRequest {

    private List<MeasurementRequest> mMeasurementRequests;

    public SaveMeasurementsRequest(Exercise exercise) {
        mMeasurementRequests = new ArrayList<>();
        for (MetricMeasurementList metricMeasurementList: exercise.getMetricMeasurementLists()) {
            for (Measurement measurement: metricMeasurementList.getMeasurements()) {
                mMeasurementRequests.add(new MeasurementRequest(measurement));
            }
        }
    }

    @Override
    public JSONArray toJson() {
        JSONArray array = new JSONArray();
        for (MeasurementRequest measurementRequest: mMeasurementRequests) {
            array.put(measurementRequest.toJson());
        }

        return array;
    }
}
