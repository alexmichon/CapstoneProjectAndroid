package edu.berkeley.capstoneproject.capstoneprojectandroid;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import timber.log.Timber;

/**
 * Created by Alex on 25/10/2017.
 */

public class CapstoneProjectAndroidApplication extends Application {

    private static CapstoneProjectAndroidApplication instance;

    private AppComponent mAppComponent;

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

        mAppComponent = DaggerAppComponent
                .builder()
                .application(this)
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public ActivityComponent getActivityComponent(BaseActivity activity) {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(activity))
                .appComponent(getAppComponent())
                .build();
    }

    public static CapstoneProjectAndroidApplication getInstance() {
        return instance;
    }
}
