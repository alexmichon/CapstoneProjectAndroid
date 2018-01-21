package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Alex on 24/12/2017.
 */

public interface IApiHeader {
    void addHeaders(Map<String, String> headers);

    Map<String, String> getHeaders();
    String getHeader(String key);
    void addHeader(String key, String value);

    Authentication getAuthentication();
    void setAuthentication(Authentication authentication);

    OkHttpClient okHttpClient();

    Interceptor interceptor();
}
