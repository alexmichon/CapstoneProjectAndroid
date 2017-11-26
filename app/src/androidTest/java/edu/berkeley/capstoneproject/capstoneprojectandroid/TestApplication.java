package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerTestComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.TestComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestAppModule;

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
    public AppComponent getAppComponent() {
        return mTestComponent;
    }
}
