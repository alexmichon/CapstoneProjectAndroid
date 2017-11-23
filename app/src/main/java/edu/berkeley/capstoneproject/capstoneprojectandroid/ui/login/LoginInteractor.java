package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

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
                        Timber.d("Converting response to user");
                        return response.getUser();
                    }
                });
    }


    private void updateApiHeader(LoginResponse response) {
        Timber.d("Updating api header");
        getDataManager().getApiHelper().getApiHeader().setAccessToken(response.getAccessToken());
        getDataManager().getApiHelper().getApiHeader().setClient(response.getClient());
        getDataManager().getApiHelper().getApiHeader().setExpiry(response.getExpiry());
        getDataManager().getApiHelper().getApiHeader().setTokenType(response.getTokenType());
        getDataManager().getApiHelper().getApiHeader().setUid(response.getUid());
    }
}
