package edu.berkeley.capstoneproject.capstoneprojectandroid;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.NetModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.Feather52;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.VolleyRequestQueue;

/**
 * Created by Alex on 25/10/2017.
 */

public class CapstoneProjectAndroidApplication extends Application {

    private static final String TAG = CapstoneProjectAndroidApplication.class.getSimpleName();

    private static CapstoneProjectAndroidApplication instance;

    private AppComponent mAppComponent;

    private final Feather52 mFeather52 = new Feather52();
    private User mCurrentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        VolleyRequestQueue.init(getApplicationContext());

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        mAppComponent = DaggerAppComponent
                .builder()
                .application(this)
                .appModule(new AppModule(this))
                //.netModule(new NetModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static CapstoneProjectAndroidApplication getInstance() {
        return instance;
    }

    public Feather52 getFeather52() {
        return mFeather52;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User user) {
        mCurrentUser = user;
    }
}
