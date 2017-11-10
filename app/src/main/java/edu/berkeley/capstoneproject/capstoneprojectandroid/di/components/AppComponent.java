package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.ActivityBuilder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.NetModule;

/**
 * Created by Alex on 08/11/2017.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, NetModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(CapstoneProjectAndroidApplication application);
        Builder appModule(AppModule appModule);
        Builder netModule(NetModule netModule);
        AppComponent build();
    }

    void inject(CapstoneProjectAndroidApplication app);
}
