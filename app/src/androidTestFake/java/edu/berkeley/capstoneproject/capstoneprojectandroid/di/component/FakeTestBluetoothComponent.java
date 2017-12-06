package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeTestBluetoothModule;

/**
 * Created by Alex on 05/12/2017.
 */

@Singleton
@Component(modules = FakeTestBluetoothModule.class)
interface FakeTestBluetoothComponent extends FakeBluetoothComponent {
}
