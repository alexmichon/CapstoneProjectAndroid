package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {LoginActivityModule.class})
public interface LoginActivityComponent extends AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {}
}
