package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.ExerciseTypesModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types.ExerciseTypesActivity;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {ExerciseTypesModule.class})
@PerActivity
public interface ExerciseTypesComponent extends AndroidInjector<ExerciseTypesActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ExerciseTypesActivity> {}
}
