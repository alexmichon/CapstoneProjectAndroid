package edu.berkeley.capstoneproject.capstoneprojectandroid.network.helpers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.measurements.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RailsServer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.VolleyRequestQueue;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.requests.ApiRequest;
import timber.log.Timber;

/**
 * Created by Alex on 27/10/2017.
 */

public class MeasurementHelper {

    private static final String TAG = MeasurementHelper.class.getSimpleName();

    public static void create(Exercise exercise, Metric metric, final Measurement measurement) {
        Timber.d("Create measurement");

        try {
            ApiRequest request = new ApiRequest(
                    Request.Method.POST,
                    RailsServer.getInstance().getMeasurementsUrl(exercise, metric).toString(),
                    measurement.toJson(),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                measurement.setID(response.getInt("id"));
                                Timber.d("Success");
                            } catch (JSONException e) {
                                Timber.e("No id found");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Timber.e(error, "Error");
                        }
                    });
            VolleyRequestQueue.getInstance().addRequest(request);
        } catch (JSONException e) {
            Timber.e(e, "JSON Exception");
        }
    }
}
