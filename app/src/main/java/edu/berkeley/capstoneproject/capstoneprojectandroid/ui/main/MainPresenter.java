package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainPresenter<V extends MainContract.View, I extends MainContract.Interactor>
        extends BasePresenter<V, I> implements MainContract.Presenter<V, I> {

    @Inject
    public MainPresenter(I interactor,
                         ISchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onStartTrainingClick() {
        getView().startTrainingActivity();
    }

    @Override
    public void onViewResultsClick() {
        getView().startResultsActivity();
    }

    @Override
    public NavigationView.OnNavigationItemSelectedListener getNavigationListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getView().showMessage("You're already here !");
                        return true;
                }
                return false;
            }
        };
    }
}
