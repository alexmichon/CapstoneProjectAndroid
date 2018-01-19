package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.CommonSessionModule;

/**
 * Created by Alex on 17/12/2017.
 */

@Singleton
@Component(modules = CommonSessionModule.class)
public interface CommonSessionComponent extends SessionComponent {
}
