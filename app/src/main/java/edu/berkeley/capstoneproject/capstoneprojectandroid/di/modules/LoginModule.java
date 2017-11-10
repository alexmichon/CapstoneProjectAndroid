package edu.berkeley.capstoneproject.capstoneprojectandroid.di.modules;

import dagger.Module;
import dagger.Provides;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;
import retrofit2.Retrofit;

/**
 * Created by Alex on 08/11/2017.
 */

@Module
public class LoginModule {

    @Provides
    LoginContract.View provideView(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    LoginContract.Presenter providePresenter(LoginContract.View view, AuthService authService) {
        return new LoginPresenter(view, authService);
    }

    @Provides
    AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
