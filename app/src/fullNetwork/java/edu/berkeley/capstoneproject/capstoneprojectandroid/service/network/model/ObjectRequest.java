package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 28/12/2017.
 */

public class ObjectRequest<T extends BaseRequest> {

    @SerializedName(value = T.SERIALIZED_NAME)
    @Expose
    private T mObject;

    public ObjectRequest(T object) {
        mObject = object;
    }
}
