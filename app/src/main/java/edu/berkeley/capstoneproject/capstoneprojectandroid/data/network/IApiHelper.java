package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.IExerciseService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public interface IApiHelper {

    Retrofit getRetrofit();
    OkHttpClient getOkHttpClient();

    IAuthService getAuthService();
    IExerciseService getExerciseService();

    ApiHeader getApiHeader();
}
