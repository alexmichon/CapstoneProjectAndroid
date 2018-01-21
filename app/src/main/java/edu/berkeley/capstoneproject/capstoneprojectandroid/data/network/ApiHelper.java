package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;
import okhttp3.OkHttpClient;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class ApiHelper implements IApiHelper {

    private final IAuthService mAuthService;
    private final IExerciseService mExerciseService;

    @Inject
    public ApiHelper(IAuthService authService, IExerciseService exerciseService) {
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
}
