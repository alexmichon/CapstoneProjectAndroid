package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.MainPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class MainModule extends BaseModule {

    @Provides
    MainContract.View provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    MainContract.Interactor provideInteractor() {
        return new MainInteractor();
    }

    @Provides
    @PerActivity
    MainContract.Presenter<MainContract.View, MainContract.Interactor>
    providePresenter(MainContract.Interactor interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        return new MainPresenter(interactor, schedulerProvider, compositeDisposable);
    }
}
