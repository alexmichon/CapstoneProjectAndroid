package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Alex on 14/01/2018.
 */

public class ObjectsRequest<T extends ListRequest> extends BaseRequest {

    private final String mKey;
    private final T mObjects;

    public ObjectsRequest(String key, T objects) {
        mKey = key;
        mObjects = objects;
    }

    public String getKey() {
        return mKey;
    }

    public T getObjects() {
        return mObjects;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(mKey, mObjects.toJson());
        } catch (JSONException e) {
            Timber.e(e);
        }
        return json;
    }
}
