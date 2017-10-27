package edu.berkeley.capstoneproject.capstoneprojectandroid;

import android.app.Application;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.Feather52;

/**
 * Created by Alex on 25/10/2017.
 */

public class CapstoneProjectAndroidApplication extends Application {

    private static final String TAG = CapstoneProjectAndroidApplication.class.getSimpleName();

    private static CapstoneProjectAndroidApplication instance;

    private final Feather52 mFeather52 = new Feather52();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static CapstoneProjectAndroidApplication getInstance() {
        return instance;
    }

    public Feather52 getFeather52() {
        return mFeather52;
    }
}
