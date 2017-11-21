package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.ApiConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 09/11/2017.
 */

@Module
public class NetModule {

    final CapstoneProjectAndroidApplication mApplication;

    public NetModule(CapstoneProjectAndroidApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Cache provideCache() {
        return new Cache(mApplication.getCacheDir(), ApiConstants.CACHE_SIZE);
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
