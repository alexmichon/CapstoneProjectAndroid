package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomeContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomeFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomeInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home.HomePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 11/11/2017.
 */

@Module
public class HomeModule extends BaseModule {

    @Provides
    HomeContract.View provideView(HomeFragment fragment) {
        return fragment;
    }

    @Provides
    HomeContract.Interactor provideInteractor() {
        return new HomeInteractor();
    }

    @Provides
    HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>
    providePresenter(HomeContract.Interactor interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        return new HomePresenter(interactor, schedulerProvider, compositeDisposable);
    }
}
