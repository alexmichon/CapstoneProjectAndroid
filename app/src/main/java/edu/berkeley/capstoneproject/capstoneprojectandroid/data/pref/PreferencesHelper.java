package edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.PreferenceInfo;
import io.reactivex.Completable;
import io.reactivex.functions.Action;

/**
 * Created by Alex on 14/12/2017.
 */

@Singleton
public class PreferencesHelper implements IPreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN   = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_CLIENT         = "PREF_KEY_CLIENT";
    private static final String PREF_KEY_EXPIRY         = "PREF_KEY_EXPIRY";
    private static final String PREF_KEY_TOKEN_TYPE     = "PREF_KEY_TOKEN_TYPE";
    private static final String PREF_KEY_UID            = "PREF_KEY_UID";


    private final SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mSharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public Authentication getAuthentication() {
        return new Authentication(
                mSharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, null),
                mSharedPreferences.getString(PREF_KEY_CLIENT, null),
                mSharedPreferences.getString(PREF_KEY_EXPIRY, null),
                mSharedPreferences.getString(PREF_KEY_TOKEN_TYPE, null),
                mSharedPreferences.getString(PREF_KEY_UID, null)
        );
    }

    @Override
    public void setAuthentication(final Authentication authentication) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREF_KEY_ACCESS_TOKEN, authentication.getAccessToken());
        editor.putString(PREF_KEY_CLIENT,       authentication.getClient());
        editor.putString(PREF_KEY_EXPIRY,       authentication.getExpiry());
        editor.putString(PREF_KEY_TOKEN_TYPE,   authentication.getTokenType());
        editor.putString(PREF_KEY_UID,          authentication.getUid());
        editor.apply();
    }
}
