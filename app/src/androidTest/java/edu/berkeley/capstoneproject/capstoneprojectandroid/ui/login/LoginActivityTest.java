package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.TestActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.TestActivityModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivityTestRule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseEspressoTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 24/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest extends BaseEspressoTest {

    @Inject
    LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> mPresenter;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final BaseActivityTestRule<LoginActivity> login =
                new BaseActivityTestRule<LoginActivity>(LoginActivity.class, true, true);

    @Before
    public void setup() {
        login.getActivityComponent().inject(this);
        unlockScreen(login.getActivity());
    }

    @Test
    public void testCheckViewsDisplay() {
        onView(withId(R.id.login_email))
                .check(matches(isDisplayed()));

        onView(withId(R.id.login_password))
                .check(matches(isDisplayed()));

        onView(withId(R.id.login_button))
                .check(matches(isDisplayed()));

        onView(withId(R.id.login_register_link))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testOnButtonClickShouldCallPresenter() {
        String email = "email";
        String password = "password";

        doNothing().when(login.getActivity().mPresenter).onLoginClick(anyString(), anyString());

        onView(withId(R.id.login_email))
                .perform(typeText(email))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.login_password))
                .perform(typeText(password))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        verify(login.getActivity().mPresenter).onLoginClick(email, password);
    }
}
