package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.FakeApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeAppModule;

/**
 * Created by Alex on 28/11/2017.
 */

@Singleton
@Component(modules = {FakeAppModule.class})
public interface FakeAppComponent extends AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(FakeApplication application);
        Builder appModule(FakeAppModule appModule);
        FakeAppComponent build();
    }

    void inject(FakeApplication app);

}
