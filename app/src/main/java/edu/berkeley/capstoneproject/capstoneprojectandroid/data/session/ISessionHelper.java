package edu.berkeley.capstoneproject.capstoneprojectandroid.data.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IUserService;

/**
 * Created by Alex on 15/12/2017.
 */

public interface ISessionHelper {

    IUserService getUserService();
    IExerciseService getExerciseService();
}
