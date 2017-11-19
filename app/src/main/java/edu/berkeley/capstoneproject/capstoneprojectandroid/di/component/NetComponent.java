package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.NetModule;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {
}
