package edu.berkeley.capstoneproject.capstoneprojectandroid;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.DaggerAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.NetModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.Feather52;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.VolleyRequestQueue;

/**
 * Created by Alex on 25/10/2017.
 */

public class CapstoneProjectAndroidApplication extends Application implements HasActivityInjector {

    private static final String TAG = CapstoneProjectAndroidApplication.class.getSimpleName();

    private static CapstoneProjectAndroidApplication instance;

    @Inject
    DispatchingAndroidInjector<Activity> mDispatchingAndroidInjector;

    private final Feather52 mFeather52 = new Feather52();
    private User mCurrentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        VolleyRequestQueue.init(getApplicationContext());

        DaggerAppComponent
                .builder()
                .application(this)
                .appModule(new AppModule(this))
                .netModule(new NetModule(this))
                .build()
                .inject(this);
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

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mDispatchingAndroidInjector;
    }
}
