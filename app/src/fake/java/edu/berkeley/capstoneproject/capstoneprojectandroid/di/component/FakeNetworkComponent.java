package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeNetworkModule;

/**
 * Created by Alex on 06/12/2017.
 */

@Singleton
@Component(modules = FakeNetworkModule.class)
public interface FakeNetworkComponent extends NetworkComponent {
}
