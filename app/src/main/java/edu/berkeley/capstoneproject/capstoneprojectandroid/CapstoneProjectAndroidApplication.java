package edu.berkeley.capstoneproject.capstoneprojectandroid;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.gsonparserfactory.GsonParserFactory;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.BluetoothComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.BluetoothComponentFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.NetworkComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.NetworkComponentFactory;
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
    private BluetoothComponent mBluetoothComponent;
    private NetworkComponent mNetworkComponent;

    private AppModule mAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mAppComponent = createAppComponent();

        AndroidNetworking.initialize(getApplicationContext(), getNetworkComponent().okHttpClient());
        AndroidNetworking.setParserFactory(new GsonParserFactory());
        if (BuildConfig.DEBUG) {
            //AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return String.format("[%s#%s:%s]", super.createStackElementTag(element), element.getMethodName(), element.getLineNumber());
                }
            });
        }
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = createAppComponent();
        }
        return mAppComponent;
    }

    public AppModule getAppModule() {
        if (mAppModule == null) {
            mAppModule = createAppModule();
        }
        return mAppModule;
    }

    public BluetoothComponent getBluetoothComponent() {
        if (mBluetoothComponent == null) {
            mBluetoothComponent = createBluetoothComponent();
        }
        return mBluetoothComponent;
    }

    public NetworkComponent getNetworkComponent() {
        if (mNetworkComponent == null) {
            mNetworkComponent = createNetworkComponent();
        }
        return mNetworkComponent;
    }



    protected AppModule createAppModule() {
        return new AppModule(this);
    }

    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .application(this)
                .appModule(getAppModule())
                .bluetoothComponent(getBluetoothComponent())
                .networkComponent(getNetworkComponent())
                .build();
    }

    protected BluetoothComponent createBluetoothComponent() {
        return BluetoothComponentFactory.create(this);
    }

    protected NetworkComponent createNetworkComponent() {
        return NetworkComponentFactory.create(this);
    }

    public ActivityComponent getActivityComponent(BaseActivity activity) {
        return DaggerActivityComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(new ActivityModule(activity))
                .build();
    }

    public static CapstoneProjectAndroidApplication getInstance() {
        return instance;
    }
}
