package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.NetModule;

/**
 * Created by Alex on 09/11/2017.
 */

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
}
