package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeNetworkModule;

/**
 * Created by Alex on 21/12/2017.
 */

public class NetworkComponentFactory {

    public static NetworkComponent create(CapstoneProjectAndroidApplication context) {
        return DaggerFakeNetworkComponent.builder()
                .appModule(new AppModule(context))
                .fakeNetworkModule(new FakeNetworkModule())
                .build();
    }
}
