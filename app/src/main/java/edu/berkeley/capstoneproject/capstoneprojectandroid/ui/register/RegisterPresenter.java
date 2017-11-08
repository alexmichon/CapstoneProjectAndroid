package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.register;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.ApiService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.network.RetroClient;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alex on 07/11/2017.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private Observable<User> mRegisterSubscription;

    @Override
    public void register(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        final ApiService apiService = RetroClient.getApiService();
        mRegisterSubscription = apiService.register(new RegisterRequest(
           email, password, passwordConfirmation, firstName, lastName
        ));

        mRegisterSubscription.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<User>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull User user) {
                    mView.onRegisterSuccess(user);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mView.onRegisterFailure();
                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    public void cancel() {
        if (mRegisterSubscription != null) {
            mRegisterSubscription.unsubscribeOn(Schedulers.io());
        }
    }
}
