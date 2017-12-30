package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import timber.log.Timber;

/**
 * Created by Alex on 15/12/2017.
 */

public abstract class AuthenticationFragmentInteractor extends BaseInteractor implements AuthenticationFragmentContract.Interactor {

    private final IAuthManager mAuthManager;

    public AuthenticationFragmentInteractor(IDataManager dataManager, IAuthManager authManager) {
        super(dataManager);
        mAuthManager = authManager;
    }

    @Override
    public void remember(boolean enabled) {
        mAuthManager.remember(enabled);
    }

    protected IAuthManager getAuthManager() {
        return mAuthManager;
    }
}
