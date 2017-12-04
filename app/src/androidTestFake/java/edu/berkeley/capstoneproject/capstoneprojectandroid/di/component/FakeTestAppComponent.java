package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.FakeTestApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeTestAppModule;

/**
 * Created by Alex on 04/12/2017.
 */

@Singleton
@Component(modules = FakeTestAppModule.class)
public interface FakeTestAppComponent extends TestAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        FakeTestAppComponent.Builder application(FakeTestApplication application);
        FakeTestAppComponent.Builder appTestModule(FakeTestAppModule appModule);
        FakeTestAppComponent build();
    }

    void inject(FakeTestApplication app);

    IDataManager dataManager();
}
