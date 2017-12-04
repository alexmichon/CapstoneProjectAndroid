package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.net.ssl.HttpsURLConnection;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.ApiConstants;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiError;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by Alex on 07/11/2017.
 */

public abstract class BasePresenter<V extends IBaseView, I extends IBaseInteractor> implements IBasePresenter<V, I> {

    private static final String TAG = BasePresenter.class.getSimpleName();

    private final ISchedulerProvider mSchedulerProvider;

    private V mView;
    private I mInteractor;

    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        mInteractor = interactor;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
        mInteractor = null;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public I getInteractor() {
        return mInteractor;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    public ISchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void handleApiError(Throwable throwable) {

        ANError error = null;
        if (throwable instanceof ANError) {
            error = (ANError) throwable;
        }
        else {
            Timber.e(throwable, "Unknown error");
        }

        if (error == null || error.getErrorBody() == null) {
            getView().showError("An error occurred");
            return;
        }

        if (error.getErrorCode() == ApiConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getView().showError("Connection error");
            return;
        }

        if (error.getErrorCode() == ApiConstants.API_STATUS_CODE_LOCAL_ERROR
                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getView().showError("Retry error");
            return;
        }

        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        try {
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError == null || apiError.getMessage() == null) {
                getView().showError("An error occurred");
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getView().showError(apiError.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Timber.e(e, "handleApiError");
            getView().showError("An error occurred");
        }
    }
}
