package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises;

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
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExercisesActivity extends BaseActivity implements ExercisesContract.View {

    @Inject
    ExercisesContract.Presenter mPresenter;

    @BindView(R.id.exercises_list)
    ListView mExerciseListView;

    private ExercisesAdapter mExercisesAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        ButterKnife.bind(this);

        mExercisesAdapter = new ExercisesAdapter(this, R.layout.row_exercise);
        mExerciseListView.setAdapter(mExercisesAdapter);
    }

    @Override
    public void addExercise(Exercise exercise) {
        mExercisesAdapter.add(exercise);
        mExercisesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(ExercisesActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @OnItemClick
    void onExerciseClick(int position) {
        mPresenter.onExerciseClick(mExercisesAdapter.getItem(position));
    }
}
