package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
