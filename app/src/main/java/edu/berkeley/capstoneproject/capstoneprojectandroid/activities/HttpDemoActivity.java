package edu.berkeley.capstoneproject.capstoneprojectandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.JsonWriter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;

/**
 * Created by Alex on 19/10/2017.
 */

public class HttpDemoActivity extends Activity {

    private static final String TAG = "Http Demo";

    private EditText editHttp;
    private Button buttonHttp;
    private TextView textHttp;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demo);

        editHttp = (EditText) findViewById(R.id.edit_http_send);
        buttonHttp = (Button) findViewById(R.id.button_http_send);
        textHttp = (TextView) findViewById(R.id.text_http_received);

        buttonHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject json = buildJson();
                if (json != null) {
                    RequestQueue queue = Volley.newRequestQueue(HttpDemoActivity.this);
                    String url ="http://192.168.1.2:3000/demo";

// Request a string response from the provided URL.
                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        textHttp.setText(response.toString(4));
                                    } catch (JSONException e) {
                                        Log.e(TAG, "Can't print JSON response", e);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    textHttp.setText("That didn't work!");
                                }
                            }
                    ) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json");
                            headers.put("Accept", "application/json");

                            return headers;
                        }
                    };
// Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });
    }



    private JSONObject buildJson() {
        JSONObject json = null;
        try {
            json = new JSONObject()
                .put("demo", new JSONObject()
                    .put("value", editHttp.getText().toString())
                    .put("date", Long.toString(System.currentTimeMillis() / 1000))
                );
        } catch (JSONException e) {
            Log.e(TAG, "Build JSON failed", e);
        }
        return json;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(HttpDemoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
