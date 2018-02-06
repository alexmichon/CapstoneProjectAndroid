package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list.ExerciseTypesFragment;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_type.ExerciseTypeFragment;

/**
 * Created by Alex on 13/01/2018.
 */

public class ExerciseBuilderFragment extends BaseFragment<ExerciseBuilderContract.View, ExerciseBuilderContract.Presenter<ExerciseBuilderContract.View, ExerciseBuilderContract.Interactor>>
    implements ExerciseBuilderContract.View, ExerciseTypeFragment.ExerciseTypeFragmentListener, ExerciseTypesFragment.ExerciseTypesFragmentListener {


    @BindView(R.id.exercise_builder_pager)
    ViewPager mPager;

    private PagerAdapter mPagerAdapter;

    private ExerciseBuilderFragmentListener mListener;

    public static ExerciseBuilderFragment newInstance(ExerciseBuilderFragmentListener listener) {
        ExerciseBuilderFragment fragment = new ExerciseBuilderFragment();
        fragment.setListener(listener);
        return fragment;
    }

    @NonNull
    @Override
    public ExerciseBuilderContract.Presenter<ExerciseBuilderContract.View, ExerciseBuilderContract.Interactor> createPresenter() {
        return getActivityComponent().exerciseBuilderPresenter();
    }

    @Override
    public void onExerciseTypeSelect(ExerciseType exerciseType) {
        getPresenter().onExerciseTypeSelect(exerciseType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_builder, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        mPagerAdapter = new ExerciseBuilderPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        return view;
    }

    @Override
    public void onExerciseBuilt() {
        hideLoading();
        if (mListener != null) {
            mListener.onExerciseBuilt();
        }
    }

    @Override
    public void onExerciseError(Throwable throwable) {
        hideLoading();
    }


    private class ExerciseBuilderPagerAdapter extends FragmentStatePagerAdapter {

        private static final int COUNT = 1;

        public ExerciseBuilderPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    ExerciseTypesFragment fragment = new ExerciseTypesFragment();
                    fragment.setListener(ExerciseBuilderFragment.this);
                    return fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }

    public void setListener(ExerciseBuilderFragmentListener listener) {
        mListener = listener;
    }

    public interface ExerciseBuilderFragmentListener {
        void onExerciseBuilt();
    }
}
