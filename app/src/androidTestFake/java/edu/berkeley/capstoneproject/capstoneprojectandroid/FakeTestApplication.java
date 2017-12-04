package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerFakeTestAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerTestActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.FakeTestAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.TestAppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeTestAppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 04/12/2017.
 */

public class FakeTestApplication extends TestApplication {

    private FakeTestAppComponent mTestComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mTestComponent = DaggerFakeTestAppComponent.builder()
                .application(this)
                .appTestModule(new FakeTestAppModule(this))
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
