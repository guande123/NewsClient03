package com.gdcp.zgd.newsclient03.activity;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.gdcp.zgd.newsclient03.R;
import com.gdcp.zgd.newsclient03.fragment.MainFragment1;
import com.gdcp.zgd.newsclient03.fragment.MainFragment2;
import com.gdcp.zgd.newsclient03.fragment.MainFragment3;
import com.gdcp.zgd.newsclient03.fragment.MainFragment4;
import com.gdcp.zgd.newsclient03.fragment.MainFragment5;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity {


    private RadioGroup radioGroup;
  private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private Toolbar mToolbar;
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override   // 点击单选时，调用此方法
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId) {
                    case R.id.rb_01:    // 点击单选时，切换ViewPager子界面
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch(position) {  // 当ViewPager子界面切换后，选中某个RadioButton
                    case 0:
                        radioGroup.check(R.id.rb_01);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_02);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_03);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_04);
                        break;
                    case 4:
                        radioGroup.check(R.id.rb_05);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_01);
        initViewPager();
        initNavigationView();
        initToolbar();
        initDrawerLayout();
    }
    private ActionBarDrawerToggle toggle;

    private void initDrawerLayout() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();     // 同步drawerLayout和toolbar的状态
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);  // 使用toolbar代替ActionBar

        mToolbar.setLogo(R.drawable.biz_news_tie_share);
        mToolbar.setTitle("ToolBar");   // 通过代码设置才生效：app:title="toolbar"

        mToolbar.setSubtitle("这是子标题");
        mToolbar.setTitleTextColor(Color.RED);
        mToolbar.setSubtitleTextColor(Color.YELLOW);

        // 导航栏图标显示
        mToolbar.setNavigationIcon(R.drawable.biz_video_progress_thumb);

        // 点击toolbar导航栏左上角的图标后，退出当前界面
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // 侧滑菜单点击监听
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // 点击侧滑菜单item时，通过DrawerLayout关闭侧滑菜单
                        showToast("" + item.getTitle());
                        drawerLayout.closeDrawers();

                        return false;
                    }
                });
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment1());
        fragments.add(new MainFragment2());
        fragments.add(new MainFragment3());
        fragments.add(new MainFragment4());
        fragments.add(new MainFragment5());

        viewPager.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }
    //================Toolbar右上角弹出菜单(begin)=======================

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_01) {
            showToast("item 01");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//================Toolbar右上角弹出菜单(end)=========================
}
