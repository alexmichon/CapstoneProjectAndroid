package edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RailsServer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.VolleyRequestQueue;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.requests.ApiRequest;

/**
 * Created by Alex on 05/11/2017.
 */

public class UserHelper {

    private static final String TAG = UserHelper.class.getSimpleName();

    public static RequestFuture<JSONObject> login(String email, String password) throws JSONException, InterruptedException, ExecutionException, TimeoutException {
        Log.d(TAG, "Create exercise");

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JSONObject jsonData = new JSONObject().put("email", email).put("password", password);
        ApiRequest request = new ApiRequest(Request.Method.POST, RailsServer.getInstance().getLoginUrl().toString(), jsonData, future, future);
        VolleyRequestQueue.getInstance().addRequest(request);

        return future;
    }
}
