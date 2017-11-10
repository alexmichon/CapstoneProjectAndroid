package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.LoginModule;

/**
 * Created by Alex on 08/11/2017.
 */

@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent extends AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {}
}
