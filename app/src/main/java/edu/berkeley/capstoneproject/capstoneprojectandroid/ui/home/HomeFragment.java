package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 10/11/2017.
 */

public class HomeFragment extends BaseFragment<HomeContract.View, HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>> implements HomeContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public HomeContract.Presenter<HomeContract.View, HomeContract.Interactor> createPresenter() {
        return null;
    }
}
