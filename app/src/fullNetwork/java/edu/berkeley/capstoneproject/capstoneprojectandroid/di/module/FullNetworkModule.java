package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IAuthInterceptor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.interceptor.ApiInterceptor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.interceptor.AuthInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Alex on 06/12/2017.
 */

@Module
public class FullNetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@Named("apiInterceptor") Interceptor apiInterceptor, @Named("authInterceptor") Interceptor authInterceptor) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(apiInterceptor)
                .addNetworkInterceptor(authInterceptor)
                .build();
    }

    @Provides
    @Singleton
    IApiHeader provideApiHeader(ApiHeader apiHeader) {
        return apiHeader;
    }


    @Provides
    @Singleton
    IAuthService provideAuthService(AuthService authService) {
        return authService;
    };

    @Provides
    @Singleton
    IExerciseService provideNetworkExerciseService(ExerciseService exerciseService) {
        return exerciseService;
    }

    @Provides
    @Singleton
    IAuthInterceptor provideAuthInterceptor(AuthInterceptor authInterceptor) {
        return authInterceptor;
    }

    @Provides
    @Singleton
    @Named("authInterceptor")
    Interceptor provideOkAuthInterceptor(AuthInterceptor authInterceptor) {
        return authInterceptor;
    }

    @Provides
    @Singleton
    @Named("apiInterceptor")
    Interceptor provideOkApiInterceptor(ApiInterceptor apiInterceptor) {
        return apiInterceptor;
    }
}
