package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.BluetoothListModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.bluetooth.BluetoothListActivity;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {BluetoothListModule.class})
@PerActivity
public interface BluetoothListComponent extends AndroidInjector<BluetoothListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BluetoothListActivity> {}
}
