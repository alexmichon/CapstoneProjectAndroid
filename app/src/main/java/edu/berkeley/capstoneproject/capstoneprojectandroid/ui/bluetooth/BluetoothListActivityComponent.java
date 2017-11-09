package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {BluetoothListActivityModule.class})
public interface BluetoothListActivityComponent extends AndroidInjector<BluetoothListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BluetoothListActivity> {}
}
