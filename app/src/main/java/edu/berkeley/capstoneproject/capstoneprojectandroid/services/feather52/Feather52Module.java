package edu.berkeley.capstoneproject.capstoneprojectandroid.services.feather52;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 18/11/2017.
 */

@Module
public class Feather52Module {

    @Provides
    @Singleton
    IFeather52Service provideFeather52Service() {
        //return new Feather52Service2();
        return null;
    }
}
