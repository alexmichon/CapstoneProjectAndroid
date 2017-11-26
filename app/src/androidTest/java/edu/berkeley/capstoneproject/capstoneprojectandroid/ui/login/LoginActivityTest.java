package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alex on 24/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public final ActivityTestRule<LoginActivity> main =
            new ActivityTestRule<LoginActivity>(LoginActivity.class, true, false);

    @Test
    public void foo() {
        int i = 42;
        assertEquals(42, i);
    }

}
