package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 10/11/2017.
 */

public class RegisterInteractor extends BaseInteractor implements RegisterContract.Interactor {

    @Inject
    public RegisterInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<User> doRegisterApiCall(RegisterRequest request) {
        return getDataManager().getApiHelper().getAuthService()
                .doRegister(request).doOnSuccess(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        updateApiHeader(registerResponse);
                    }
                })
                .map(new Function<RegisterResponse, User>() {
                    @Override
                    public User apply(@NonNull RegisterResponse registerResponse) throws Exception {
                        return registerResponse.getUser();
                    }
                });
    }

    private void updateApiHeader(RegisterResponse response) {
        getDataManager().getApiHelper().getApiHeader().rebuild()
                .accessToken(response.getAccessToken())
                .client(response.getClient())
                .expiry(response.getExpiry())
                .tokenType(response.getTokenType())
                .uid(response.getUid())
                .build();
    }
}
