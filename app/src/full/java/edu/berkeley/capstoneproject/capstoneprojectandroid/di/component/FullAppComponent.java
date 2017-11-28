package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.FullApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullAppModule;

/**
 * Created by Alex on 27/11/2017.
 */

@Singleton
@Component(modules = FullAppModule.class)
public interface FullAppComponent extends AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(FullApplication application);
        Builder appModule(FullAppModule appModule);
        FullAppComponent build();
    }

    void inject(FullApplication app);
}
