package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Alex on 10/11/2017.
 */

public class RegisterInteractor extends BaseInteractor implements RegisterContract.Interactor {

    @Inject
    public RegisterInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<RegisterResponse> doRegisterApiCall(RegisterRequest request) {
        return getDataManager().getApiHelper().getAuthService()
                .doRegisterApiCall(request);
    }
}
