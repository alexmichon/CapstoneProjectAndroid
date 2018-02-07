package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream;

import io.reactivex.Completable;

/**
 * Created by Alex on 16/01/2018.
 */

public interface IStream {

    Completable connect();
    Completable disconnect();
    void send(String ...string);
}
