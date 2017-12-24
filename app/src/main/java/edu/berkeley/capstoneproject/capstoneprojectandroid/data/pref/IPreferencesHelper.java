package edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;

/**
 * Created by Alex on 14/12/2017.
 */

public interface IPreferencesHelper {

    Authentication getAuthentication();
    void setAuthentication(Authentication authentication);

    void removeAuthentication();
}
