package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 15/12/2017.
 */

public abstract class AuthenticationFragmentInteractor extends BaseInteractor implements AuthenticationFragmentContract.Interactor {

    public AuthenticationFragmentInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void doRemember(User user) {
        getDataManager().getPreferencesHelper().setAuthentication(user.getAuthentication());
    }
}
