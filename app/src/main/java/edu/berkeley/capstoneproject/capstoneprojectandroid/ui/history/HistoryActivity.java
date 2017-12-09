package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history;

import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises.HistoryExercisesFragment;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryActivity extends DrawerActivity<HistoryContract.View, HistoryContract.Presenter<HistoryContract.View, HistoryContract.Interactor>> implements HistoryContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setUnbinder(ButterKnife.bind(this));
        if (savedInstanceState == null) {
            setFragment(R.id.history_container, new HistoryExercisesFragment());
        }
    }

    @NonNull
    @Override
    public HistoryContract.Presenter<HistoryContract.View, HistoryContract.Interactor> createPresenter() {
        return getActivityComponent().historyPresenter();
    }
}
