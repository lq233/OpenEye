package com.example.lq.eyes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lq.eyes.R;
import com.example.lq.eyes.fragment.DayFragment;
import com.example.lq.eyes.fragment.FindFragment;
import com.example.lq.eyes.fragment.HotFragment;
import com.example.lq.eyes.widget.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_time)
    CustomTextView mToolbarTime;
    @BindView(R.id.main_toolbar_iv_right)
    ImageButton mMainToolbarIvRight;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.main_frame)
    FrameLayout mMainFrame;
    @BindView(R.id.tv_daily)
    TextView mTvDaily;
    @BindView(R.id.tv_find)
    TextView mTvFind;
    @BindView(R.id.tv_hot)
    TextView mTvHot;
    @BindView(R.id.main_menu)
    LinearLayout mMainMenu;
    @BindView(R.id.main_nav)
    NavigationView mMainNav;
    @BindView(R.id.main_dl)
    DrawerLayout mMainDl;
    private FragmentManager mManager;
    private long mExitTime = 0;
    private FragmentTransaction mTransaction;
    private DayFragment dailyFragment;
    private FindFragment moreFragment;
    private HotFragment hotFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mManager = getSupportFragmentManager();
        initView();
    }

    private void setCuritem(int currentItem) {
        mTransaction = mManager.beginTransaction();
        HideFragment(mTransaction);//隐藏Fragment
        clearChioce();
        switch (currentItem) {
            case 1:
                mToolbarTime.setVisibility(View.VISIBLE);
                mMainToolbarIvRight.setImageResource(R.drawable.main_toolbar_eye_selector);
                mTvDaily.setTextColor(getResources().getColor(R.color.colorBlack));
                if (dailyFragment == null) {
                    dailyFragment = new DayFragment();
                    mTransaction.add(R.id.main_frame, dailyFragment);

                } else {
                    mTransaction.show(dailyFragment);
                }

                break;

            case 2://发现更多
                mMainToolbarIvRight.setImageResource(R.mipmap.ic_action_search);
                mMainToolbarIvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    }
                });
                mToolbarTime.setVisibility(View.GONE);
                mTvFind.setTextColor(getResources().getColor(R.color.colorBlack));
                if (moreFragment == null) {
                    moreFragment = new FindFragment();
                    mTransaction.add(R.id.main_frame, moreFragment);
                } else {
                    mTransaction.show(moreFragment);

                }
                break;

            case 3://热门排行
                mMainToolbarIvRight.setImageResource(R.drawable.main_toolbar_eye_selector);
                mToolbarTime.setVisibility(View.GONE);
                mTvHot.setTextColor(getResources().getColor(R.color.colorBlack));
                if (hotFragment == null) {
                    hotFragment = new HotFragment();
                    mTransaction.add(R.id.main_frame, hotFragment);
                } else {
                    mTransaction.show(hotFragment);
                }
                break;
        }
        mTransaction.commit();//提交事务
    }

    private void HideFragment(FragmentTransaction transaction) {
        if (dailyFragment != null) {
            transaction.hide(dailyFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
        if (hotFragment != null) {
            transaction.hide(hotFragment);
        }
    }

    private void initView() {
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mMainDl, mToolBar, R.string.app_name, R.string.app_name);
        mMainDl.addDrawerListener(toggle);
        toggle.syncState();
        setCuritem(1);
        mMainDl.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mMainDl.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
                super.onDrawerSlide(drawerView, slideOffset);
            }
        });

    }

    private void clearChioce() {
        //还原默认选项
        mTvDaily.setTextColor(getResources().getColor(R.color.colorGray));
        mTvFind.setTextColor(getResources().getColor(R.color.colorGray));
        mTvHot.setTextColor(getResources().getColor(R.color.colorGray));
    }

    // 按两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tv_daily, R.id.tv_find, R.id.tv_hot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_daily:
                setCuritem(1);
                break;
            case R.id.tv_find:
                setCuritem(2);
                break;
            case R.id.tv_hot:
                setCuritem(3);
                break;
        }
    }
}
