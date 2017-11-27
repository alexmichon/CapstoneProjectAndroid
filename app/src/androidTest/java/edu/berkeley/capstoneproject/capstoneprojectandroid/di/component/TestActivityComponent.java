package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import dagger.Component;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.ActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginActivityTest;

/**
 * Created by Alex on 25/11/2017.
 */

@PerActivity
@Component(dependencies = {TestComponent.class}, modules = {ActivityModule.class})
public interface TestActivityComponent extends ActivityComponent {

    void inject(LoginActivityTest activity);
}
