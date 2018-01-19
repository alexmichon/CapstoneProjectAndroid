package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by Alex on 24/12/2017.
 */

public abstract class NetworkService {

    private final OkHttpClient mOkHttpClient;

    public NetworkService(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
