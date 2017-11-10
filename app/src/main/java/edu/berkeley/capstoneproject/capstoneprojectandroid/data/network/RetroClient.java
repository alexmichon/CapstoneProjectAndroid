package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.AuthService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 07/11/2017.
 */

public class RetroClient {

    private static final String URL = "http://192.168.1.2:3000/";

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public AuthService getApiService() {
        return getRetrofitInstance().create(AuthService.class);
    }
}
