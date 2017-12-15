package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;

/**
 * Created by Alex on 15/12/2017.
 */

public class AuthenticationInteractor extends BaseInteractor implements AuthenticationContract.Interactor {

    @Inject
    public AuthenticationInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
