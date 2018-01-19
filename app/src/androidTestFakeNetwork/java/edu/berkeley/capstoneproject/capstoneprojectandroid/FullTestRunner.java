package edu.berkeley.capstoneproject.capstoneprojectandroid;

import android.app.Application;
import android.content.Context;

/**
 * Created by Alex on 04/12/2017.
 */

public class FullTestRunner extends TestRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(
                cl, FullTestApplication.class.getName(), context);
    }
}
