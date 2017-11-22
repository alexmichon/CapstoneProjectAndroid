package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import java.io.IOException;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.ApiConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.IExerciseService;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public class ApiHelper implements IApiHelper {

    private static final String TAG = ApiHelper.class.getSimpleName();

    private final ApiHeader mApiHeader;

    private final IAuthService mAuthService;
    private final IExerciseService mExerciseService;

    @Inject
    public ApiHelper(ApiHeader apiHeader, IAuthService authService, IExerciseService exerciseService) {
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
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }
}
