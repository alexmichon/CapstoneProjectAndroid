package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises.ExercisesActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises.ExercisesContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises.ExercisesPresenter;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class ExercisesModule {

    @Provides
    ExercisesContract.View provideView(ExercisesActivity activity) {
        return activity;
    }

    @Provides
    ExercisesContract.Presenter providePresenter(ExercisesContract.View view) {
        return new ExercisesPresenter(view);
    }
}
