package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.FullTestApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullTestAppModule;

/**
 * Created by Alex on 04/12/2017.
 */

@Singleton
@Component(modules = FullTestAppModule.class)
public interface FullTestAppComponent extends TestAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        FullTestAppComponent.Builder application(FullTestApplication application);
        FullTestAppComponent.Builder appTestModule(FullTestAppModule appModule);
        FullTestAppComponent build();
    }

    void inject(FullTestApplication app);

    IDataManager dataManager();
}
