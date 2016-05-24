package com.wxxiaomi.ming.teachingoffice2.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.MenuItem;
import com.wxxiaomi.ming.teachingoffice2.R;
import com.wxxiaomi.ming.teachingoffice2.presenter.MainPre;
import com.wxxiaomi.ming.teachingoffice2.presenter.impl.MainPreImpl;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseActivity;
import com.wxxiaomi.ming.teachingoffice2.ui.common.BaseFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.common.FragmentCallback;
import com.wxxiaomi.ming.teachingoffice2.ui.fragment.BorrowStateFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.fragment.EleCourseFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.fragment.IndexFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.fragment.ScoreFragment;
import com.wxxiaomi.ming.teachingoffice2.ui.view.MainView;

public class MainActivity extends BaseActivity implements MainView,FragmentCallback{

    private ActionBarDrawerToggle mDrawerToggle; // 定义toolbar左上角的弹出左侧菜单按钮
    private DrawerLayout drawer_main; // 定义左侧滑动布局，其实就是主布局
    private NavigationView navigationView;
    private Fragment contentFragment; // 记录当前正在使用的fragment
    private MenuItem currentItem;

    private MainPre mainPre;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        drawer_main = (DrawerLayout) findViewById(R.id.dl_left);
        mainPre = new MainPreImpl(this);
        initFragment(savedInstanceState);
    }

    @Override
    public void initData() {
        initNavigationView();
    }

    private void initNavigationView() {
        currentItem = navigationView.getMenu().findItem(R.id.drawer_home);
        currentItem.setChecked(true);
        navigationView
                .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if (currentItem.getItemId() != menuItem.getItemId()) {
                            mainPre.switchFragment(menuItem);
                        }
                        drawer_main.closeDrawers();
                        return true;
                    }

                });
    }

    @Override
    public void showUnLoginDialog() {
        showMsgDialog("当前未登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainPre.onUnLoginDialogOkClick();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainPre.onUnLoginDialogCancelClick();
                    }
                });
    }

    @Override
    public void runLoginAct() {
        Intent intent = new Intent(ct,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMenuItemChange(final MenuItem menuItem) {
        boolean flag = changFragmentById(menuItem);
        if (flag) {
            currentItem.setChecked(false);
            menuItem.setChecked(true);
            currentItem = menuItem;
        }
    }

    @Override
    public boolean changFragmentById(MenuItem menuItem) {
        boolean f = true;
        switch (menuItem.getItemId()) {
            case R.id.drawer_score:
//                setToolBarTitle("成绩查询");
                switchFragment(new ScoreFragment());
                break;
            case R.id.drawer_home:
//                setToolBarTitle("首页");
//                switchFragment(new IndexFragment(), 0);
                break;
            case R.id.drawer_classform:
                break;
            case R.id.drawer_elective:
//                setToolBarTitle("选课情况");
                switchFragment(new EleCourseFragment());
                break;
            case R.id.drawer_lib_borrow_state:
//                setToolBarTitle("借阅情况");
                switchFragment(new BorrowStateFragment());
                break;
            case R.id.drawer_lib_search:
                Intent intent = new Intent(ct,SearchActivity.class);
                startActivity(intent);
                f = false;
            default:
                break;
        }
        return f;
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            contentFragment = new IndexFragment();
        } else {
            // 取出之前保存的contentFragment
            contentFragment = this.getSupportFragmentManager().getFragment(
                    savedInstanceState, "contentFragment");
        }
        // 设置当前的fragment
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, contentFragment).commit();
    }

    public void switchFragment(Fragment f) {
        contentFragment = f;
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, contentFragment).commit();
        invalidateOptionsMenu();
        /**
         * 关闭左侧滑出菜单
         */
        drawer_main.closeDrawers();
    }

    @Override
    public void onFragmentCallback(BaseFragment fragment, int id, Bundle args) {

    }
}
