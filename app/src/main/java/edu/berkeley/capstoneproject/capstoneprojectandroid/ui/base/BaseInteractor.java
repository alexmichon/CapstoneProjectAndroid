package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;

/**
 * Created by Alex on 09/11/2017.
 */

public class BaseInteractor implements IBaseInteractor {

    private final IDataManager mDataManager;

    @Inject
    public BaseInteractor(IDataManager dataManager) {
        mDataManager = dataManager;
    }

    protected IDataManager getDataManager() {
        return mDataManager;
    }
}
