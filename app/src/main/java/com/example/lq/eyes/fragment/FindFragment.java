package com.example.lq.eyes.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lq.eyes.R;
import com.example.lq.eyes.adapter.MoreAdapter;
import com.example.lq.eyes.base.BaseFragment;
import com.example.lq.eyes.bean.MoreBean;
import com.example.lq.eyes.presenter.MorePresenter;
import com.example.lq.eyes.view.MyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment<MyView, MorePresenter<MyView>> implements MyView {


    @BindView(R.id.more_rlv)
    RecyclerView mMoreRlv;
    Unbinder unbinder;
    private MoreAdapter mAdapter;

    @Override
    protected void initView() {
        super.initView();
        ArrayList<MoreBean> list = new ArrayList<>();
        mMoreRlv.setLayoutManager(new GridLayoutManager(getContext(),2));
        mAdapter = new MoreAdapter(getContext(),list);
        mMoreRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getMore();
    }

    @Override
    public void onSuccess(List<MoreBean> bean) {
        mAdapter.addData(bean);
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    protected MorePresenter<MyView> initPresenter() {
        return new MorePresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_more;
    }



}
