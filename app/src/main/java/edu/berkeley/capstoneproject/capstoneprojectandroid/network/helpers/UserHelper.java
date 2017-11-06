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
        Log.d(TAG, "Create login request");

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JSONObject jsonData = new JSONObject().put("email", email).put("password", password);
        ApiRequest request = new ApiRequest(Request.Method.POST, RailsServer.getInstance().getLoginUrl().toString(), jsonData, future, future);
        VolleyRequestQueue.getInstance().addRequest(request);

        return future;
    }

    public static RequestFuture<JSONObject> register(String email, String password, String passwordConfirmation, String firstName, String lastName) throws JSONException {
        Log.d(TAG, "Create register request");

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JSONObject data = new JSONObject()
                .put("email", email)
                .put("password", password)
                .put("password_confirmation", passwordConfirmation)
                .put("first_name", firstName)
                .put("last_name", lastName);

        ApiRequest request = new ApiRequest(Request.Method.POST, RailsServer.getInstance().getRegisterUrl().toString(), data, future, future);
        VolleyRequestQueue.getInstance().addRequest(request);

        return future;
    }
}
