package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.toolbar;

import android.support.annotation.LayoutRes;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 09/11/2017.
 */

public abstract class ToolbarActivity<V extends IBaseView, P extends IBasePresenter<V, ?>> extends BaseActivity<V, P> {

    private Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_toolbar);
        final FrameLayout container = findViewById(R.id.toolbar_container);

        LayoutInflater.from(this).inflate(layoutResID, container, true);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    public void showToolbar() {
        mToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        mToolbar.setVisibility(View.GONE);
    }
}
