package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.TestApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestAppModule;

/**
 * Created by Alex on 24/11/2017.
 */

@Singleton
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        TestAppComponent.Builder application(TestApplication application);
        TestAppComponent.Builder appTestModule(TestAppModule appModule);
        TestAppComponent build();
    }

    void inject(TestApplication app);

    IDataManager dataManager();
}
