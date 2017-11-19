package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.ApiConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class ApiHelper implements IApiHelper {

    @Inject
    public ApiHelper() {

    }

    @Override
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstants.API_URL)
                .client(getOkHttpClient())
                .build();
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    @Override
    public AuthService getAuthService() {
        return getRetrofit().create(AuthService.class);
    }
}
