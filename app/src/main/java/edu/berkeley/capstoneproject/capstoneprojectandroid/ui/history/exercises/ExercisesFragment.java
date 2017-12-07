package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.HistoryContract;

/**
 * Created by Alex on 06/12/2017.
 */

public class ExercisesFragment extends BaseFragment<ExercisesContract.View, ExercisesContract.Presenter<ExercisesContract.View, ExercisesContract.Interactor>> implements ExercisesContract.View {

    private static final String TITLE = "Exercises";

    @BindView(R.id.exercises_list)
    ListView mExercisesView;

    private ExercisesAdapter mExercisesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        ButterKnife.bind(this, view);

        mExercisesAdapter = new ExercisesAdapter(getContext(), R.layout.row_exercise);
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
    public ExercisesContract.Presenter<ExercisesContract.View, ExercisesContract.Interactor> createPresenter() {
        return getActivityComponent().exercisesPresenter();
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
