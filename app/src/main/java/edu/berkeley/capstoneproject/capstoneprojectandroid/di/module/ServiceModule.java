package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import android.app.Service;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.BaseService;

/**
 * Created by Alex on 18/11/2017.
 */

@Module
public class ServiceModule {

    private final BaseService mService;

    public ServiceModule(BaseService service) {
        mService = service;
    }
}
