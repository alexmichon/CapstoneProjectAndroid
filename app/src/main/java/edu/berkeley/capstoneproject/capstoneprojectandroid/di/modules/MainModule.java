package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainPresenter;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class MainModule {

    @Provides
    MainContract.View provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    MainContract.Presenter providePresenter(MainContract.View view) {
        return new MainPresenter(view);
    }
}
