package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;

/**
 * Created by Alex on 19/11/2017.
 */

public class MeasurementResponse {

    @SerializedName("id")
    @Expose
    private int mId;

    public Measurement toMeasurement() {
        return null;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
