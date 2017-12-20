package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IExerciseCreatorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IUserService;

/**
 * Created by Alex on 17/12/2017.
 */

public interface SessionComponent {
    IUserService userService();
    IExerciseCreatorService exerciseService();
}
