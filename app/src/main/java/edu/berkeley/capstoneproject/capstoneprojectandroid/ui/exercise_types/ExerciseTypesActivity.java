package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar.ToolbarActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.ExerciseActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExerciseTypesActivity extends ToolbarActivity implements ExerciseTypesContract.View {

    @Inject
    ExerciseTypesContract.Presenter<ExerciseTypesContract.View, ExerciseTypesContract.Interactor> mPresenter;

    @BindView(R.id.exercises_list)
    ListView mExerciseListView;

    private ExerciseTypesAdapter mExercisesAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        ButterKnife.bind(this);

        mExercisesAdapter = new ExerciseTypesAdapter(this, R.layout.row_exercise);
        mExerciseListView.setAdapter(mExercisesAdapter);

        mPresenter.onAttach(this);
    }

    @OnItemClick(R.id.exercises_list)
    void onExerciseClick(int position) {
        mPresenter.onExerciseTypeClick(mExercisesAdapter.getItem(position));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onLoadExerciseTypes();
    }

    @Override
    public void addExerciseType(ExerciseType exerciseType) {
        mExercisesAdapter.add(exerciseType);
        mExercisesAdapter.notifyDataSetChanged();
    }

    @Override
    public void startExerciseTypeActivity(ExerciseType exerciseType) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra(ExerciseActivity.EXTRA_EXERCISE_TYPE, exerciseType);
        startActivity(intent);
        finish();
    }

    @Override
    public void onExerciseTypeSelected(ExerciseType exerciseType) {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
