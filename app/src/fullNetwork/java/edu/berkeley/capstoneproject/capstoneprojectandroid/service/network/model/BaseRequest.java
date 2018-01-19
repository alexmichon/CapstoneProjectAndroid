package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

/**
 * Created by Alex on 14/01/2018.
 */

public abstract class BaseRequest {

    public JSONObject toJson() {
        try {
            return new JSONObject(new Gson().toJson(this));
        } catch (JSONException e) {
            Timber.e(e);
            return new JSONObject();
        }
    }
}
