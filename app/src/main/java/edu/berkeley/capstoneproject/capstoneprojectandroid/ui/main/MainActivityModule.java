package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class MainActivityModule {

    @Provides
    MainContract.View provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    MainContract.Presenter providePresenter(MainContract.View view) {
        return new MainPresenter(view);
    }
}
