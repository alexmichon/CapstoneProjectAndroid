package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseTypeRepositoryImpl;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesPresenter;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class ExerciseTypesModule {

    @Provides
    ExerciseTypesContract.View provideView(ExerciseTypesActivity activity) {
        return activity;
    }

    @Provides
    ExerciseTypeRepository provideRepository() {
        return new ExerciseTypeRepositoryImpl();
    }

    @Provides
    ExerciseTypesContract.Presenter providePresenter(ExerciseTypesContract.View view, ExerciseTypeRepository repository) {
        return new ExerciseTypesPresenter(view, repository);
    }
}
