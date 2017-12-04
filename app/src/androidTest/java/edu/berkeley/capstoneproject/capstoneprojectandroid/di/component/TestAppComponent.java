package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.TestApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestAppModule;

/**
 * Created by Alex on 04/12/2017.
 */

public interface TestAppComponent extends AppComponent {

/*    @Component.Builder
    interface Builder {
        @BindsInstance
        TestAppComponent.Builder application(TestApplication application);
        TestAppComponent.Builder appTestModule(TestAppModule appModule);
        TestAppComponent build();
    }

    void inject(TestApplication app);*/
}
