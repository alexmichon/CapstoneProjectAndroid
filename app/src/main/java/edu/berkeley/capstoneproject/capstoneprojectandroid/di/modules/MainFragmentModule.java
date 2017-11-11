package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.components.HomeComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomeFragment;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public abstract class MainFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> provideFragment(HomeComponent.Builder builder);
}
