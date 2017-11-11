package edu.berkeley.capstoneproject.capstoneprojectandroid.di.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.HomeModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules.MainModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomeFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;

/**
 * Created by Alex on 11/11/2017.
 */

@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent extends AndroidInjector<HomeFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeFragment>{}
}