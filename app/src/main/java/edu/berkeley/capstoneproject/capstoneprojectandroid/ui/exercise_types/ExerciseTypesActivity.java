package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_types;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import dagger.android.AndroidInjection;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.ToolbarActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExerciseTypesActivity extends ToolbarActivity implements ExerciseTypesContract.View {

    @Inject
    ExerciseTypesContract.Presenter mPresenter;

    @BindView(R.id.exercises_list)
    ListView mExerciseListView;

    private ExerciseTypesAdapter mExercisesAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        ButterKnife.bind(this);

        mExercisesAdapter = new ExerciseTypesAdapter(this, R.layout.row_exercise);
        mExerciseListView.setAdapter(mExercisesAdapter);
    }

    @OnItemClick(R.id.exercises_list)
    void onExerciseClick(int position) {
        mPresenter.onExerciseTypeClick(mExercisesAdapter.getItem(position));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadExerciseTypes();
    }

    @Override
    public void addExerciseType(ExerciseType exerciseType) {
        mExercisesAdapter.add(exerciseType);
        mExercisesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(ExerciseTypesActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startExerciseTypeActivity(ExerciseType exerciseType) {
        // TODO
    }
}
