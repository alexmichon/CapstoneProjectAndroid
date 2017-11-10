package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class LoginModule extends BaseModule {

    @Provides
    LoginContract.View provideView(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    LoginContract.Interactor provideInteractor(AuthService service) {
        return new LoginInteractor(service);
    }

    @Provides
    @PerActivity
    LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>
    providePresenter(LoginContract.Interactor interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        return new LoginPresenter(interactor, schedulerProvider, compositeDisposable);
    }

    @Provides
    AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
