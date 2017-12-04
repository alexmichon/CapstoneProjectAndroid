package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import dagger.Module;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.TestApplication;

/**
 * Created by Alex on 04/12/2017.
 */

public class TestAppModule extends AppModule {
    public TestAppModule(TestApplication application) {
        super(application);
    }
}
