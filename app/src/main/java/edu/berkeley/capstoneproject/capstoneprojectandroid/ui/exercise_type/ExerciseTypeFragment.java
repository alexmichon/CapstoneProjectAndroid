package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 17/11/2017.
 */

public class ExerciseTypeFragment extends BaseFragment<ExerciseTypeContract.View, ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor>> implements ExerciseTypeContract.View {

    private static final String TITLE = "Exercise Types";

    @BindView(R.id.exercises_list)
    ListView mExerciseListView;

    private ExerciseTypeAdapter mExercisesAdapter;
    private ExerciseTypesFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercises, container, false);

        ButterKnife.bind(this, view);

        mExercisesAdapter = new ExerciseTypeAdapter(getContext(), R.layout.row_exercise);
        mExerciseListView.setAdapter(mExercisesAdapter);

        return view;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onLoadExerciseTypes();
    }

    @NonNull
    @Override
    public ExerciseTypeContract.Presenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseTypePresenter();
    }

    @Override
    public void addExerciseType(ExerciseType exerciseType) {
        mExercisesAdapter.add(exerciseType);
        mExercisesAdapter.notifyDataSetChanged();
    }

    @Override
    public void startExerciseTypeActivity(ExerciseType exerciseType) {
        // TODO
    }

    @OnItemClick(R.id.exercises_list)
    void onExerciseClick(int position) {
        getPresenter().onExerciseTypeClick(mExercisesAdapter.getItem(position));
    }

    @Override
    public void onExerciseTypeSelected(ExerciseType exerciseType) {
        if (mListener != null) {
            mListener.onExerciseTypeSelected(exerciseType);
        }
    }

    @Override
    public void onExerciseTypesLoading() {
        showLoading();
    }

    @Override
    public void onExerciseTypesDoneLoading() {
        hideLoading();
    }


    public void setListener(ExerciseTypesFragmentListener listener) {
        mListener = listener;
    }

    public interface ExerciseTypesFragmentListener {
        void onExerciseTypeSelected(ExerciseType exerciseType);
    }
}