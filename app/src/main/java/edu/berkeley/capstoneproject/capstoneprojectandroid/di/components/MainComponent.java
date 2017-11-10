package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.MainModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {MainModule.class})
@PerActivity
public interface MainComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
