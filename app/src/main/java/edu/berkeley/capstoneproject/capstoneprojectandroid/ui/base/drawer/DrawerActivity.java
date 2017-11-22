package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

/**
 * Created by Alex on 10/11/2017.
 */

public class DrawerActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_navigation_view)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_drawer);
        final FrameLayout container = (FrameLayout) findViewById(R.id.drawer_container);
        LayoutInflater.from(this).inflate(layoutResID, container, true);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
    }

    public void setNavigationDrawerMenu(@MenuRes int menu, NavigationView.OnNavigationItemSelectedListener listener) {
        mNavigationView.getMenu().clear();
        mNavigationView.inflateMenu(menu);
        mNavigationView.setNavigationItemSelectedListener(listener);
    }

    public void setNavigationDrawerHeader(@LayoutRes int layout) {
        mNavigationView.inflateHeaderView(layout);
    }

    public void setNavigationDrawerHeader(View view) {
        mNavigationView.getHeaderView(mNavigationView.getHeaderCount() - 1).setVisibility(View.GONE);
        mNavigationView.addHeaderView(view);
    }


    public void initNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

}
