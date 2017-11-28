package edu.berkeley.capstoneproject.capstoneprojectandroid;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import timber.log.Timber;

/**
 * Created by Alex on 25/10/2017.
 */

public abstract class CapstoneProjectAndroidApplication extends Application {

    private static CapstoneProjectAndroidApplication instance;

    protected AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return element + ":" + element.getLineNumber();
                }
            });
            Log.i("Timber", Timber.asTree().toString());
            Timber.i("Timber enabled");
        }
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public abstract ActivityComponent getActivityComponent(BaseActivity activity);

    public static CapstoneProjectAndroidApplication getInstance() {
        return instance;
    }
}
