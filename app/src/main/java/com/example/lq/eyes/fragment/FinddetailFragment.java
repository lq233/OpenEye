package com.example.lq.eyes.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lq.eyes.R;
import com.example.lq.eyes.adapter.DetailRlvAdapter;
import com.example.lq.eyes.base.BaseFragment;
import com.example.lq.eyes.bean.DetailBean;
import com.example.lq.eyes.presenter.DetailPresenter;
import com.example.lq.eyes.view.DetailView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class FinddetailFragment extends BaseFragment<DetailView, DetailPresenter<DetailView>> implements DetailView {

    private final String type;
    @BindView(R.id.find_rlv)
    RecyclerView mFindRlv;
    Unbinder unbinder;
    private DetailRlvAdapter mAdapter;

    public FinddetailFragment(String title) {
        type=title;
    }

    @Override
    protected void initView() {
        super.initView();
        ArrayList<DetailBean.ItemListBeanX> list = new ArrayList<>();
        mFindRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new DetailRlvAdapter(getContext(), list,type);
        mFindRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getDetail();
    }

    @Override
    public void onSuccess(DetailBean bean) {
        mAdapter.addData(bean);
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    protected DetailPresenter<DetailView> initPresenter() {
        return new DetailPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_finddetail;
    }
}
