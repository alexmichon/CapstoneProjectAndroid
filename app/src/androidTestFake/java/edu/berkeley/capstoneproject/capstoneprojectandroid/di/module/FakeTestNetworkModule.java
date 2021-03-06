package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;

/**
 * Created by Alex on 05/12/2017.
 */

@Module
public class FakeTestNetworkModule extends FakeNetworkModule {

    @Provides
    @Singleton
    IAuthService provideDeviceService() {
        return Mockito.mock(IAuthService.class);
    }

    @Provides
    @Singleton
    IExerciseService provideExerciseService() {
        return Mockito.mock(IExerciseService.class);
    }

}
