package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 08/12/2017.
 */

public class HistoryExerciseFragment extends BaseFragment<HistoryExerciseContract.View, HistoryExerciseContract.Presenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor>>
    implements HistoryExerciseContract.View {

    private Exercise mExercise;

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public HistoryExerciseContract.Presenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor> createPresenter() {
        return getActivityComponent().historyExercisePresenter();
    }
}
