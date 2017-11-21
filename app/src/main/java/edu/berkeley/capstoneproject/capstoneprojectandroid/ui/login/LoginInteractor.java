package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 10/11/2017.
 */

public class LoginInteractor extends BaseInteractor implements LoginContract.Interactor {

    private static final String TAG = LoginInteractor.class.getSimpleName();

    @Inject
    public LoginInteractor(IDataManager dataManager) {
        super(dataManager);
    }


    @Override
    public Single<User> doLoginCall(LoginRequest loginRequest) {
        return getDataManager().getApiHelper().getAuthService()
                .doLogin(loginRequest).doOnSuccess(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        updateApiHeader(loginResponse);
                    }
                })
                .map(new Function<LoginResponse, User>() {
                    @Override
                    public User apply(@NonNull LoginResponse response) throws Exception {
                        Log.d(TAG, "Converting response to user");
                        return response.getUser();
                    }
                });
    }


    private void updateApiHeader(LoginResponse response) {
        Log.d(TAG, "Updating api header");
        getDataManager().getApiHelper().getApiHeader().rebuild()
                .accessToken(response.getAccessToken())
                .client(response.getClient())
                .expiry(response.getExpiry())
                .tokenType(response.getTokenType())
                .uid(response.getUid())
                .build();
    }
}
