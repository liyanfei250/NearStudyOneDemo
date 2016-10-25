package com.example.lzq.near.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.example.lzq.near.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import app.AbsSuperApplication;
import base.BaseActivity;
import fragment.HomePageFragment;
import fragment.WaiteFragment;
import fragment.NearlyFragment;
import fragment.PersonCenterFragment;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;
import rx.Observable;
import rx.functions.Action1;
import thinkfreely.changemodelibrary.ChangeModeController;
import util.UpdateUtils;

public class MainActivity extends BaseActivity {

    int[] testColors = {0xFF00796B, 0xFF5B4947, 0xFF607D8B, 0xFFF57C00, 0xFFF57C00};
    Controller controller;
    List<Fragment> mFragments;
    private boolean isFirst = true;
    private long lastTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ChangeModeController.getInstance().init(this,R.attr.class).setTheme(this, R.style.DayTheme, R.style.NightTheme);
//        ChangeModeController.getInstance().init(this.getActivity(),R.attr.class).setTheme(this.getActivity(), R.style.DayTheme, R.style.NightTheme);
//        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //进入界面检查是否需要更新，可以在beseactivty中执行操作
        UpdateUtils.checkUpdate(this, false);
        initFragment();
        initTab();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(new HomePageFragment());
        mFragments.add(new NearlyFragment());
        mFragments.add(new WaiteFragment());
        mFragments.add(new PersonCenterFragment());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, mFragments.get(0));
        transaction.commit();
    }

    private void initTab() {
        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);

        //用TabItemBuilder构建一个导航按钮
        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
                .setDefaultIcon(android.R.drawable.ic_menu_send)
                .setText("首页")
                .setSelectedColor(testColors[0])
                .setTag("A")
                .build();

        //构建导航栏,得到Controller进行后续控制
        controller = pagerBottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(android.R.drawable.ic_menu_compass, "附近", testColors[1])
                .addTabItem(android.R.drawable.ic_menu_search, "购物车", testColors[2])
                .addTabItem(android.R.drawable.ic_menu_help, "个人", testColors[3])
                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();

        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                LogUtils.i("asd", "onSelected:" + index + "   TAG: " + tag.toString());

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, mFragments.get(index));
                transaction.commit();
            }

            @Override
            public void onRepeatClick(int index, Object tag) {
                LogUtils.i("asd", "onRepeatClick:" + index + "   TAG: " + tag.toString());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ChangeModeController.onDestory();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        // 双击返回键关闭程序
        // 如果两秒重置时间内再次点击返回,则退出程序
        if (doubleBackToExitPressedOnce) {
            AbsSuperApplication.finishAllActivity();
            return;
        }

        doubleBackToExitPressedOnce = true;
        showToast("再按一次返回键关闭程序");
        Observable.just(null)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        // 延迟两秒后重置标志位为false
                        doubleBackToExitPressedOnce = false;
                    }
                });
    }
}
