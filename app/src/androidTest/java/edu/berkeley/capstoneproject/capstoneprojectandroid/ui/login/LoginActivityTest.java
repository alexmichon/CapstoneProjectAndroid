package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login.LoginFragment;
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

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final ActivityTestRule<AuthenticationActivity> mAuthenticationTestRule =
                new ActivityTestRule<>(AuthenticationActivity.class, true, true);

    private LoginFragment mLoginFragment;

    @Before
    public void setup() {
        unlockScreen(mAuthenticationTestRule.getActivity());
        setupFragment();
    }

    public void setupFragment() {
        mLoginFragment = new LoginFragment();
        FragmentManager fragmentManager = mAuthenticationTestRule.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.authentication_container, mLoginFragment)
                .commit();

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
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

        doNothing().when(mLoginFragment.getPresenter()).onLoginClick(anyString(), anyString());

        onView(withId(R.id.login_email))
                .perform(typeText(email))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.login_password))
                .perform(typeText(password))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        verify(mLoginFragment.getPresenter()).onLoginClick(email, password);
    }
}
