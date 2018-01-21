package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IAuthInterceptor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;
import okhttp3.OkHttpClient;

/**
 * Created by Alex on 05/12/2017.
 */

@Module(includes = AppModule.class)
public class FakeNetworkModule {

    @Provides
    @Singleton
    IApiHelper provideApiHelper(ApiHelper apiHelper) {
        return apiHelper;
    }

    @Provides
    @Singleton
    IApiHeader provideApiHeader(ApiHeader apiHeader) {
        return apiHeader;
    }

    @Provides
    @Singleton
    IAuthInterceptor provideAuthInterceptor() {
        return new IAuthInterceptor() {
            @Override
            public void setAuthentication(Authentication auth) {

            }

            @Override
            public Authentication getAuthentication() {
                return null;
            }

            @Override
            public void setListener(Listener listener) {

            }
        };
    }

    @Provides
    @Singleton
    IAuthService provideAuthService(AuthService service) {
        return service;
    }

    @Provides
    @Singleton
    IExerciseService provideExerciseService(ExerciseService service) {
        return service;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}
