package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import edu.berkeley.capstoneproject.capstoneprojectandroid.TestApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;

/**
 * Created by Alex on 26/11/2017.
 */

public class BaseActivityTestRule<T extends BaseActivity> extends ActivityTestRule<T> {

    private ActivityComponent mActivityComponent;
    private boolean mMockMode = true;

    public BaseActivityTestRule(Class<T> activityClass) {
        super(activityClass);
    }

    public BaseActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
        super(activityClass, initialTouchMode);
    }

    public BaseActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
    }

    public void setMockMode(boolean mockMode) {
        mMockMode = mockMode;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        /*mActivityComponent = DaggerTestActivityComponent.builder()
                .appComponent(getApplication().getAppComponent())
                .activityModule(new TestActivityModule(getActivity(), mMockMode))
                .build();
                */
        return super.apply(base, description);
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    private TestApplication getApplication() {
        return (TestApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}
