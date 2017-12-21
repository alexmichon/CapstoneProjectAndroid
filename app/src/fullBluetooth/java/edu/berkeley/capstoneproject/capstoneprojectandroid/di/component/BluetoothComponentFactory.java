package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.AppModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullBluetoothModule;

/**
 * Created by Alex on 21/12/2017.
 */

public class BluetoothComponentFactory {

    public static BluetoothComponent create(CapstoneProjectAndroidApplication context) {
        return DaggerFullBluetoothComponent.builder()
                .appModule(new AppModule(context))
                .fullBluetoothModule(new FullBluetoothModule())
                .build();
    }
}
