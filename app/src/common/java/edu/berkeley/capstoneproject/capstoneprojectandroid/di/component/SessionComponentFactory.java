package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import edu.berkeley.capstoneproject.capstoneprojectandroid.CapstoneProjectAndroidApplication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.module.CommonSessionModule;

/**
 * Created by Alex on 21/12/2017.
 */

public class SessionComponentFactory {

    public static SessionComponent create(CapstoneProjectAndroidApplication context) {
        return DaggerCommonSessionComponent.builder()
                .commonSessionModule(new CommonSessionModule())
                .build();
    }
}
