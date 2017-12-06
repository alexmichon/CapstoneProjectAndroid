package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;

/**
 * Created by Alex on 08/11/2017.
 */

@Singleton
@Component(modules = {AppModule.class}, dependencies = BluetoothComponent.class)
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(CapstoneProjectAndroidApplication app);
        Builder appModule(AppModule appModule);
        Builder bluetoothComponent(BluetoothComponent bluetoothComponent);
        AppComponent build();
    }

    void inject(CapstoneProjectAndroidApplication app);

    IDataManager dataManager();
}
