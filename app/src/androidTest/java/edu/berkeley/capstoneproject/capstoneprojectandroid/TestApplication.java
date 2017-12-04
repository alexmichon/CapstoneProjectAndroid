package edu.berkeley.capstoneproject.capstoneprojectandroid;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.TestAppComponent;

/**
 * Created by Alex on 04/12/2017.
 */

public abstract class TestApplication extends CapstoneProjectAndroidApplication {

    public abstract TestAppComponent getAppComponent();

}
