package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullNetworkModule;

/**
 * Created by Alex on 21/12/2017.
 */

public class NetworkComponentFactory {

    public static NetworkComponent create(CapstoneProjectAndroidApplication context) {
        return DaggerFullNetworkComponent.builder()
                .fullNetworkModule(new FullNetworkModule())
                .build();
    }
}
