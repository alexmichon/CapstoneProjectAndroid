package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 09/11/2017.
 */

public class LoginPresenterSuiteFailure {




}
