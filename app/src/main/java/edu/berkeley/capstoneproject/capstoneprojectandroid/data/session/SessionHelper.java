package edu.berkeley.capstoneproject.capstoneprojectandroid.data.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IUserService;

/**
 * Created by Alex on 15/12/2017.
 */

@Singleton
public class SessionHelper implements ISessionHelper {

    private IUserService mUserService;
    private IExerciseService mExerciseService;

    @Inject
    public SessionHelper(IUserService userService, IExerciseService exerciseService) {
        mUserService = userService;
        mExerciseService = exerciseService;
    }

    @Override
    public IUserService getUserService() {
        return mUserService;
    }

    @Override
    public IExerciseService getExerciseService() {
        return mExerciseService;
    }
}
