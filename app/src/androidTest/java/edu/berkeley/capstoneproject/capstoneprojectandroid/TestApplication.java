package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerTestActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerTestComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.TestComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 24/11/2017.
 */

public class TestApplication extends CapstoneProjectAndroidApplication {

    private TestComponent mTestComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mTestComponent = DaggerTestComponent.builder()
                .application(this)
                .appTestModule(new TestAppModule(this))
                .build();

        mTestComponent.inject(this);
    }

    @Override
    public TestComponent getAppComponent() {
        return mTestComponent;
    }

    @Override
    public ActivityComponent getActivityComponent(BaseActivity activity) {
        return DaggerTestActivityComponent.builder()
                .activityModule(new TestActivityModule(activity, true))
                .testComponent(getAppComponent())
                .build();
    }
}
