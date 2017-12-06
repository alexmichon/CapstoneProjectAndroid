package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeTestNetworkModule;

/**
 * Created by Alex on 05/12/2017.
 */

@Singleton
@Component(modules = FakeTestNetworkModule.class)
interface FakeTestNetworkComponent extends FakeNetworkComponent {
}
