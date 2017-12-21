package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullNetworkModule;

/**
 * Created by Alex on 06/12/2017.
 */

@Singleton
@Component(modules = FullNetworkModule.class)
public interface FullNetworkComponent extends NetworkComponent {
}
