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

public interface AppComponent {

    void inject(CapstoneProjectAndroidApplication app);

    IDataManager dataManager();

}
