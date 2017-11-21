package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import java.io.IOException;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.ApiConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.IExerciseService;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class ApiHelper implements IApiHelper {

    private static final String TAG = ApiHelper.class.getSimpleName();

    private final ApiHeader mApiHeader;

    private final IAuthService mAuthService;
    private final IExerciseService mExerciseService;

    @Inject
    public ApiHelper(ApiHeader apiHeader, IAuthService authService, IExerciseService exerciseService) {
        mApiHeader = apiHeader;
        mAuthService = authService;
        mExerciseService = exerciseService;
    }

    @Override
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
                .client(getOkHttpClient())
                .build();
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder requestBuilder = chain.request().newBuilder();
                        requestBuilder.header("Content-Type", "application/json");
                        requestBuilder.header("Accept", "application/json");

                        return chain.proceed(requestBuilder.build());
                    }
                })
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        return null;
                    }
                })
                .build();
    }

    @Override
    public IAuthService getAuthService() {
        return mAuthService;
    }

    @Override
    public IExerciseService getExerciseService() {
        return mExerciseService;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }
}
