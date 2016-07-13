package com.xs.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xs.view.MyViewPager;
import com.xs.view.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //页面控件定义
    public Fragment1 fragment1 = new Fragment1();
    public Fragment2 fragment2 = new Fragment2();
    public Fragment3 fragment3 = new Fragment3();
    public Fragment4 fragment4 = new Fragment4();

    private MyViewPager mViewPager;
    private String[] mTitles = new String[4];
    private List<Fragment> mFragments = new ArrayList<>();
    private MyViewPagerAdapter mViewPagerAdapter;

    //主体框架控件
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //标题栏
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //浮动按钮
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //抽屉侧滑的动作
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //抽屉中导航按钮
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //初始化页面容器
        initThePage();


    }

    private void initThePage()
    {
        mTitles[0] = "page1";
        mTitles[1] = "page2";
        mTitles[2] = "page3";
        mTitles[3] = "page4";
        mFragments.add(0,fragment1);
        mFragments.add(1,fragment2);
        mFragments.add(2,fragment3);
        mFragments.add(3,fragment4);

        mViewPager = (MyViewPager) findViewById(R.id.id_viewpager);
        mViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //更新抽屉按钮的选中状态。
                int id = position;
                switch (id)
                {
                    case 0:navigationView.setCheckedItem(R.id.nav_camera);break;
                    case 1:navigationView.setCheckedItem(R.id.nav_gallery);break;
                    case 2:navigationView.setCheckedItem(R.id.nav_slideshow);break;
                    case 3:navigationView.setCheckedItem(R.id.nav_manage);break;
                    default:break;
                }
                //刷新标题
                toolbar.setTitle(mTitles[id]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        //按下后退键之后，先关闭抽屉
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 切换页面
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            mViewPager.setCurrentItem(0);
        } else if (id == R.id.nav_gallery) {
            mViewPager.setCurrentItem(1);
        } else if (id == R.id.nav_slideshow) {
            mViewPager.setCurrentItem(2);
        } else if (id == R.id.nav_manage) {
            mViewPager.setCurrentItem(3);
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainActivity.this,ScrollingActivity.class));
        } else if (id == R.id.nav_send) {

        }
        //切换完页面之后，关闭抽屉
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
