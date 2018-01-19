package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history;

import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise.HistoryExerciseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises.HistoryExercisesFragment;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryActivity extends DrawerActivity<HistoryContract.View, HistoryContract.Presenter<HistoryContract.View, HistoryContract.Interactor>> implements HistoryContract.View, HistoryExercisesFragment.HistoryExercisesFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setUnbinder(ButterKnife.bind(this));
        if (savedInstanceState == null) {
            showHistoryExercisesFragment();
        }
    }

    @NonNull
    @Override
    public HistoryContract.Presenter<HistoryContract.View, HistoryContract.Interactor> createPresenter() {
        return getActivityComponent().historyPresenter();
    }

    protected void setFragment(BaseFragment fragment) {
        super.setFragment(R.id.history_container, fragment);
    }

    @Override
    public void showHistoryExercisesFragment() {
        setFragment(HistoryExercisesFragment.newInstance(this));
    }

    @Override
    public void showHistoryExerciseFragment(Exercise exercise) {
        setFragment(HistoryExerciseFragment.newInstance(exercise));
    }


    @Override
    public void onHistoryExerciseSelect(Exercise exercise) {
        getPresenter().onHistoryExerciseSelect(exercise);
    }
}
