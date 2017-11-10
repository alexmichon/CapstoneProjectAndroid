package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.ExercisesModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises.ExercisesActivity;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {ExercisesModule.class})
public interface ExercisesComponent extends AndroidInjector<ExercisesActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ExercisesActivity> {}
}
