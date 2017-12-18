package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.ExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IUserService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.UserService;

/**
 * Created by Alex on 17/12/2017.
 */

@Module(includes = AppModule.class)
public class FakeSessionModule {


    @Provides
    @Singleton
    IUserService provideUserService(UserService service) {
        return service;
    }

    @Provides
    @Singleton
    IExerciseService provideExerciseService(ExerciseService service) {
        return service;
    }
}
