package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

/**
 * Created by Alex on 28/12/2017.
 */

public class BaseRequest<T> {

    private final String mKey;
    private final T mObject;

    public BaseRequest(String key, T object) {
        mKey = key;
        mObject = object;
    }

    public String getKey() {
        return mKey;
    }

    public T getObject() {
        return mObject;
    }
}
