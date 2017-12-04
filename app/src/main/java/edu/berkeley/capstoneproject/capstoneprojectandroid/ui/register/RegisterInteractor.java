package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
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
    public Single<User> doRegisterApiCall(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        return getDataManager().getApiHelper().getAuthService()
                .doRegister(email, password, passwordConfirmation, firstName, lastName).doOnSuccess(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        updateApiHeader(user);
                    }
                });
    }

    private void updateApiHeader(User user) {
        // TODO
    }
}
