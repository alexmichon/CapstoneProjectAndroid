package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FullSessionModule;

/**
 * Created by Alex on 21/12/2017.
 */

@Singleton
@Component(modules = FullSessionModule.class)
public interface FullSessionComponent extends SessionComponent {
}
