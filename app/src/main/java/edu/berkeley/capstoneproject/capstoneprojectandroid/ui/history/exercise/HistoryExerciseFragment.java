package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import android.os.Bundle;
import android.support.annotation.NonNull;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 08/12/2017.
 */

public class HistoryExerciseFragment extends BaseFragment<HistoryExerciseContract.View, HistoryExerciseContract.Presenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor>>
    implements HistoryExerciseContract.View {

    private static final String KEY_EXERCISE = "KEY_EXERCISE";

    private Exercise mExercise;


    public static HistoryExerciseFragment newInstance(Exercise exercise) {
        HistoryExerciseFragment fragment = new HistoryExerciseFragment();

        Bundle args = new Bundle();
        args.putParcelable(KEY_EXERCISE, exercise);
        fragment.setArguments(args);

        return fragment;
    }


    @NonNull
    @Override
    public HistoryExerciseContract.Presenter<HistoryExerciseContract.View, HistoryExerciseContract.Interactor> createPresenter() {
        return getActivityComponent().historyExercisePresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mExercise = args.getParcelable(KEY_EXERCISE);

        if (mExercise == null) {
            Timber.e("Exercise can't be null");
        }
    }

    @Override
    public String getTitle() {
        return null;
    }
}
