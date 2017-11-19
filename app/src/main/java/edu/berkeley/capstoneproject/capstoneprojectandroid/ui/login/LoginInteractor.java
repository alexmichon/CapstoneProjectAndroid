package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class LoginInteractor extends BaseInteractor implements LoginContract.Interactor {

    @Inject
    public LoginInteractor(IDataManager dataManager) {
        super(dataManager);
    }


    @Override
    public Observable<LoginResponse> doLoginCall(LoginRequest loginRequest) {
        return getDataManager().getApiHelper().getAuthService()
                .doLoginApiCall(loginRequest);
    }
}
