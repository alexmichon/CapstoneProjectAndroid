package edu.berkeley.capstoneproject.capstoneprojectandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.AppComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.DaggerServiceComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ServiceComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ServiceModule;

/**
 * Created by Alex on 18/11/2017.
 */

public abstract class BaseService {

    private static final String TAG = BaseService.class.getSimpleName();

    private ServiceComponent mServiceComponent;

    public BaseService(CapstoneProjectAndroidApplication application) {
        mServiceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this))
                .appComponent(application.getAppComponent())
                .build();
    }

    public ServiceComponent getServiceComponent() {
        return mServiceComponent;
    }

}
