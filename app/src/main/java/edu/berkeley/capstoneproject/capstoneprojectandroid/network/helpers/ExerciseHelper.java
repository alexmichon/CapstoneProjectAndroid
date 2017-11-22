package edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RailsServer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.VolleyRequestQueue;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.requests.ApiRequest;
import timber.log.Timber;

/**
 * Created by Alex on 27/10/2017.
 */

public class ExerciseHelper {

    private static final String TAG = ExerciseHelper.class.getSimpleName();

    public static void create(Exercise exercise) {
        Timber.d("Create exercise");

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        try {
            Timber.d("Url: " + RailsServer.getInstance().getExercisesUrl().toString());
            Timber.d("JSON: " + exercise.toJson().toString());

            ApiRequest request = new ApiRequest(Request.Method.POST, RailsServer.getInstance().getExercisesUrl().toString(), exercise.toJson(), future, future);
            VolleyRequestQueue.getInstance().addRequest(request);

            JSONObject response = future.get(30, TimeUnit.SECONDS);
            exercise.setID(response.getInt("id"));

            Timber.d("Done");

        } catch (JSONException e) {
            Timber.e(e, "JSON Exception");
        } catch (InterruptedException e) {
            Timber.e(e, "Interrupted Exception");
        } catch (ExecutionException e) {
            Timber.e("Execution Exception");
        } catch (TimeoutException e) {
            Timber.e(e, "Timeout Exception");
        }
    }
}
