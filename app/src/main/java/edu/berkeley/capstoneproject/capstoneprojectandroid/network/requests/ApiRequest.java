package edu.berkeley.capstoneproject.capstoneprojectandroid.network.requests;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 27/10/2017.
 */

public class ApiRequest extends JsonObjectRequest {

    private static final String TAG = ApiRequest.class.getSimpleName();

    private Map<String, String> mResponseHeaders;

    public ApiRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        mResponseHeaders = response.headers;
        return super.parseNetworkResponse(response);
    }

    public Map<String, String> getResponseHeaders() {
        return mResponseHeaders;
    }
}
