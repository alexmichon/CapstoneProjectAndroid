package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

/**
 * Created by Alex on 30/12/2017.
 */

public interface IAuthInterceptor {

    void setAuthentication(Authentication auth);
    Authentication getAuthentication();

    void setListener(Listener listener);

    interface Listener {
        void onAuthUpdate(Authentication auth);
    }

}
