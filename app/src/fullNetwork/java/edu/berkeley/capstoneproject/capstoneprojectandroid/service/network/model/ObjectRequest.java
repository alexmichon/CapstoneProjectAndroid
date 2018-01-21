package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

/**
 * Created by Alex on 28/12/2017.
 */

public class ObjectRequest<T extends BaseRequest> extends BaseRequest {

    private final String mKey;
    private final T mObject;

    public ObjectRequest(String key, T object) {
        mKey = key;
        mObject = object;
    }

    public String getKey() {
        return mKey;
    }

    public T getObject() {
        return mObject;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(mKey, mObject.toJson());
        } catch (JSONException e) {
            Timber.e(e);
        }
        return json;
    }
}
