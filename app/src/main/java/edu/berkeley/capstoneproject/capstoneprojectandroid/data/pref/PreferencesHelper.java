package edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.PreferenceInfo;

/**
 * Created by Alex on 14/12/2017.
 */

@Singleton
public class PreferencesHelper implements IPreferencesHelper {

    private final SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mSharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


}
