package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFakeAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 27/11/2017.
 */

public class FakeApplication extends CapstoneProjectAndroidApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerFakeAppComponent.builder()
                .application(this)
                .appModule(new FakeAppModule(this))
                .build();
    }
}
