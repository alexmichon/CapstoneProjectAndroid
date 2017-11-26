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
public interface TestComponent extends AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        TestComponent.Builder application(TestApplication application);
        TestComponent.Builder appTestModule(TestAppModule appModule);
        TestComponent build();
    }

    void inject(TestApplication app);

    IDataManager dataManager();
}
