package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class ApiHelper implements IApiHelper {

    private final IApiHeader mApiHeader;

    private final IAuthService mAuthService;
    private final IExerciseService mExerciseService;

    @Inject
    public ApiHelper(IApiHeader apiHeader, IAuthService authService, IExerciseService exerciseService) {
        mApiHeader = apiHeader;
        mAuthService = authService;
        mExerciseService = exerciseService;
    }

    @Override
    public IAuthService getAuthService() {
        return mAuthService;
    }

    @Override
    public IExerciseService getExerciseService() {
        return mExerciseService;
    }

    @Override
    public IApiHeader getApiHeader() {
        return mApiHeader;
    }
}
