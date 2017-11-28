package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFullAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 27/11/2017.
 */

public class FullApplication extends CapstoneProjectAndroidApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerFullAppComponent
                .builder()
                .application(this)
                .appModule(new FullAppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    @Override
    public ActivityComponent getActivityComponent(BaseActivity activity) {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(activity))
                .appComponent(getAppComponent())
                .build();
    }
}
