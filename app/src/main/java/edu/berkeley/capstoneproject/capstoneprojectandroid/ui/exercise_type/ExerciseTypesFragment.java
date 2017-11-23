package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type;

import android.os.Bundle;
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

public class ExerciseTypesFragment extends BaseFragment implements ExerciseTypesContract.View {

    private static final String TAG = ExerciseTypesFragment.class.getSimpleName();

    private static final String TITLE = "Exercise Types";

    @Inject
    ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor> mPresenter;

    @BindView(R.id.exercises_list)
    ListView mExerciseListView;

    private ExerciseTypesAdapter mExercisesAdapter;
    private ExerciseTypesFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercises, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);

            mExercisesAdapter = new ExerciseTypesAdapter(getContext(), R.layout.row_exercise);
            mExerciseListView.setAdapter(mExercisesAdapter);

            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onLoadExerciseTypes();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
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
        mPresenter.onExerciseTypeClick(mExercisesAdapter.getItem(position));
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