package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.ApiService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RetroClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alex on 06/11/2017.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private Call<User> mLoginService;

    @Override
    public void login(String email, String password) {
        mView.onLoginTry();

        final ApiService apiService = RetroClient.getApiService();
        mLoginService = apiService.login(new LoginRequest(email, password));
        mLoginService.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    response.headers();
                    mView.onLoginSuccess(user);
                }
                else {
                    mView.onLoginFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mView.onLoginFailure();
            }
        });
    }

    @Override
    public void cancel() {
        if (mLoginService != null) {
            mLoginService.cancel();
        }
    }
}
