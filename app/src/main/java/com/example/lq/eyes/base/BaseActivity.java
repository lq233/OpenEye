package com.example.lq.eyes.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 003 on 2019/5/15.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {

    private Unbinder mUnbinder;
    public P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(createLayout(), null);
        setContentView(view);
        mUnbinder = ButterKnife.bind(this);
        presenter = initPresenter();
        presenter.attachView((V) this);
        initView();
        initListener();
        initData();
    }

    protected abstract P initPresenter();

    public void initData() {

    }

    public void initListener() {

    }

    protected abstract void initView();

    //必须重写的方法获得试图
    protected abstract int createLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter == null) {
            mUnbinder.unbind();
            presenter.deachView();
            presenter = null;
        }
    }
}
