package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryExercisesFragment extends BaseFragment<HistoryExercisesContract.View, HistoryExercisesContract.Presenter<HistoryExercisesContract.View, HistoryExercisesContract.Interactor>> implements HistoryExercisesContract.View {

    private static final String TITLE = "Exercises";

    @BindView(R.id.exercises_list)
    ListView mExercisesView;

    private HistoryExercisesAdapter mExercisesAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_exercises, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        mExercisesAdapter = new HistoryExercisesAdapter(getContext(), R.layout.row_exercise, Locale.getDefault());
        mExercisesView.setAdapter(mExercisesAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().loadExercises();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public HistoryExercisesContract.Presenter<HistoryExercisesContract.View, HistoryExercisesContract.Interactor> createPresenter() {
        return getActivityComponent().historyExercisesPresenter();
    }

    @Override
    public void addExercise(Exercise exercise) {
        mExercisesAdapter.add(exercise);
        mExercisesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadingExercises(OnCancelListener cancelListener) {
        showLoading("Loading exercises...", cancelListener);
    }

    @Override
    public void onExercisesLoaded() {
        hideLoading();
    }
}
