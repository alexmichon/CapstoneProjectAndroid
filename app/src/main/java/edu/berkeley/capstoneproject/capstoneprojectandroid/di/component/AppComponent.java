package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.feather52.Feather52Service2;

/**
 * Created by Alex on 08/11/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(CapstoneProjectAndroidApplication application);
        Builder appModule(AppModule appModule);
        //Builder netModule(NetModule netModule);
        AppComponent build();
    }

    void inject(CapstoneProjectAndroidApplication app);
    void inject(Feather52Service2 service);

    IDataManager dataManager();

}
