package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentPresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 07/11/2017.
 */

public class RegisterPresenter<V extends RegisterContract.View, I extends RegisterContract.Interactor>
        extends AuthenticationFragmentPresenter<V, I> implements RegisterContract.Presenter<V, I> {


    @Inject
    public RegisterPresenter(I interactor,
                             ISchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onRegisterClick(final String email, final String password, String passwordConfirmation, final String firstName, final String lastName) {
        if (isViewAttached()) {
            getView().onAuthenticationStart(new IBaseView.OnCancelListener() {
                @Override
                public void onCancel() {
                    onAuthenticationCancel();
                }
            });
        }

        getCompositeDisposable().add(getInteractor()
                .doRegisterApiCall(email, password, passwordConfirmation, firstName, lastName)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        onAuthenticationSuccess(user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onAuthenticationFailure(throwable);
                    }
                })
        );
    }
}
