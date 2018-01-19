package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 10/11/2017.
 */

public class HomeFragment extends BaseFragment<HomeContract.View, HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>> implements HomeContract.View {

    private static final String TITLE = "Home";


    private HomeFragmentListener mListener;



    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public static HomeFragment newInstance(HomeFragmentListener listener) {
        HomeFragment fragment = new HomeFragment();
        fragment.setListener(listener);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @NonNull
    @Override
    public HomeContract.Presenter<HomeContract.View, HomeContract.Interactor> createPresenter() {
        return getActivityComponent().homePresenter();
    }

    @OnClick(R.id.home_start_training)
    void onStartExerciseClick() {
        getPresenter().onStartTrainingClick();
    }

    @OnClick(R.id.home_view_results)
    void onViewResultsClick() {
        getPresenter().onViewResultsClick();
    }

    @Override
    public void startTrainingActivity() {
        if (mListener != null) {
            mListener.onStartTrainingActivity();
        }
    }

    @Override
    public void startResultsActivity() {
        if (mListener != null) {
            mListener.onStartResultsActivity();
        }
    }


    public void setListener(HomeFragmentListener listener) {
        mListener = listener;
    }

    public interface HomeFragmentListener {
        void onStartTrainingActivity();
        void onStartResultsActivity();
    }
}
