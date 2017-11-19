package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alex on 17/11/2017.
 */

public class TrainingPresenter<V extends TrainingContract.View, I extends TrainingContract.Interactor>
        extends BasePresenter<V,I> implements TrainingContract.Presenter<V, I> {

    private static final String TAG = TrainingPresenter.class.getSimpleName();

    @Inject
    public TrainingPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public NavigationView.OnNavigationItemSelectedListener getNavigationListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_training_bluetooth_device:
                        getView().showMessage("You're already here !");
                        return true;
                }
                return false;
            }
        };
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        getView().showBluetoothListFragment();
    }
}
