package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 20/11/2017.
 */

public class ApiError extends Error {

    public static final int ERROR_UNAUTHORIZED = 401;
    public static final int ERROR_FORBIDDEN = 403;
    public static final int ERROR_NOT_FOUND = 404;

    @Expose
    @SerializedName("status_code")
    private int mStatusCode;

    @Expose
    @SerializedName("mMessage")
    private String mMessage;

    public ApiError(int statusCode, String message) {
        this.mStatusCode = statusCode;
        this.mMessage = message;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ApiError apiError = (ApiError) object;

        if (mStatusCode != apiError.mStatusCode) {
            return false;
        }

        return mMessage != null ? mMessage.equals(apiError.mMessage) : apiError.mMessage == null;

    }
}