package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeBluetoothModule;

/**
 * Created by Alex on 05/12/2017.
 */

@Singleton
@Component(modules = FakeBluetoothModule.class)
interface FakeBluetoothComponent extends BluetoothComponent {


}
