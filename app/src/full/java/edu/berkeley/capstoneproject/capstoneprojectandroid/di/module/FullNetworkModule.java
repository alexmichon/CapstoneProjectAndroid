package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;

/**
 * Created by Alex on 06/12/2017.
 */

@Module
public class FullNetworkModule {

    @Provides
    @Singleton
    IAuthService provideAuthService(AuthService authService) {
        return authService;
    };

    @Provides
    @Singleton
    IExerciseService provideNetworkExerciseService(ExerciseService exerciseService) {
        return exerciseService;
    }
}
