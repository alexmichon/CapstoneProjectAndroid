package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.FakeSessionModule;

/**
 * Created by Alex on 17/12/2017.
 */

@Singleton
@Component(modules = FakeSessionModule.class)
public interface FakeSessionComponent extends SessionComponent {
}
