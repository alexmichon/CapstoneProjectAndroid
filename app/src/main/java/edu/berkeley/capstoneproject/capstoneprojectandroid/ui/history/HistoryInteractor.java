package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryInteractor extends BaseInteractor implements HistoryContract.Interactor {

    @Inject
    public HistoryInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
