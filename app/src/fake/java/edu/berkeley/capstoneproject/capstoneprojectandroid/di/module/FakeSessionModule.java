package edu.berkeley.capstoneproject.capstoneprojectandroid.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.ExerciseCreatorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IExerciseCreatorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.ITrainingService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IUserService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.TrainingService;
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
    IExerciseCreatorService provideExerciseService(ExerciseCreatorService service) {
        return service;
    }

    @Provides
    @Singleton
    ITrainingService provideTrainingService(TrainingService service) {
        return service;
    }
}
