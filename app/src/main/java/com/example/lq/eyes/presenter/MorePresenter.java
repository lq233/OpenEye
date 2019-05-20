package com.example.lq.eyes.presenter;

import com.example.lq.eyes.base.BasePresenter;
import com.example.lq.eyes.bean.MoreBean;
import com.example.lq.eyes.model.MoreModel;
import com.example.lq.eyes.view.MyView;

import java.util.List;

/**
 * Created by 003 on 2019/5/17.
 */

public class MorePresenter<V extends MyView> extends BasePresenter<V> implements MoreModel.CallBack {
    private MoreModel mMoreModel = new MoreModel();

    public void getMore() {
        mMoreModel.getMore(this);
    }

    @Override
    public void getData(List<MoreBean> moreBean) {
        mView.onSuccess(moreBean);
    }
}
