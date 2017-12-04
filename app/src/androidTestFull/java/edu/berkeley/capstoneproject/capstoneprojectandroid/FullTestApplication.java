package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFullTestAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerTestActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.FullTestAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.TestAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullTestAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 04/12/2017.
 */

public class FullTestApplication extends TestApplication {

    private FullTestAppComponent mTestComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mTestComponent = DaggerFullTestAppComponent.builder()
                .application(this)
                .appTestModule(new FullTestAppModule(this))
                .build();

        mTestComponent.inject(this);
    }

    @Override
    public TestAppComponent getAppComponent() {
        return mTestComponent;
    }

    @Override
    public ActivityComponent getActivityComponent(BaseActivity activity) {
        return DaggerTestActivityComponent.builder()
                .activityModule(new TestActivityModule(activity, true))
                .testAppComponent(getAppComponent())
                .build();
    }
}
