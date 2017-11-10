package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import android.content.Context;
import android.net.ConnectivityManager;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash.SplashPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 10/11/2017.
 */

@Module
public class SplashModule extends BaseModule {

    @Provides
    SplashContract.View provideView(SplashActivity activity) {
        return activity;
    }

    @Provides
    SplashContract.Interactor provideInteractor() {
        return new SplashInteractor();
    }

    @Provides
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @PerActivity
    SplashContract.Presenter<SplashContract.View, SplashContract.Interactor>
    providePresenter(SplashContract.Interactor interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, ConnectivityManager connectivityManager) {
        return new SplashPresenter<SplashContract.View, SplashContract.Interactor>(interactor, schedulerProvider, compositeDisposable, connectivityManager);
    }
}
