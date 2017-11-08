package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.ApiService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RetroClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alex on 07/11/2017.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private Call<User> mRegisterService;

    @Override
    public void register(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        final ApiService apiService = RetroClient.getApiService();
        mRegisterService = apiService.register(new RegisterRequest(
           email, password, passwordConfirmation, firstName, lastName
        ));

        mRegisterService.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    mView.onRegisterSuccess(response.body());
                }
                else {
                    mView.onRegisterFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mView.onRegisterFailure();
            }
        });
    }

    @Override
    public void cancel() {
        if (mRegisterService != null) {
            mRegisterService.cancel();
        }
    }
}
