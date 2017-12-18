package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IUserService;

/**
 * Created by Alex on 17/12/2017.
 */

public interface SessionComponent {
    IUserService userService();
    IExerciseService exerciseService();
}
