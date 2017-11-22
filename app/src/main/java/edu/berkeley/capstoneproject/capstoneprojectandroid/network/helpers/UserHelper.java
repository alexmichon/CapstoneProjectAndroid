package edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RailsServer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.VolleyRequestQueue;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.requests.ApiRequest;
import timber.log.Timber;

/**
 * Created by Alex on 05/11/2017.
 */

public class UserHelper {

    public static ApiRequest login(User user, RequestFuture<JSONObject> future) throws JSONException {
        Timber.d("Create onLoginClick request");

        if (future == null) {
            future = RequestFuture.newFuture();
        }

        JSONObject jsonData = new JSONObject().put("email", user.getEmail()).put("password", user.getPassword());
        ApiRequest request = new ApiRequest(Request.Method.POST, RailsServer.getInstance().getLoginUrl().toString(), jsonData, future, future);
        VolleyRequestQueue.getInstance().addRequest(request);

        return request;
    }

    public static ApiRequest register(User user, RequestFuture<JSONObject> future) throws JSONException {
        Timber.d("Create doRegisterApiCall request");

        if (future == null) {
            future = RequestFuture.newFuture();
        }

        JSONObject data = new JSONObject()
                .put("email", user.getEmail())
                .put("password", user.getPassword())
                .put("password_confirmation", user.getPasswordConfirmation())
                .put("first_name", user.getFirstName())
                .put("last_name", user.getLastName());

        ApiRequest request = new ApiRequest(Request.Method.POST, RailsServer.getInstance().getRegisterUrl().toString(), data, future, future);
        VolleyRequestQueue.getInstance().addRequest(request);

        return request;
    }
}
