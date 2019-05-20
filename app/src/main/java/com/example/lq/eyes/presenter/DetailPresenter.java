package com.example.lq.eyes.presenter;

import com.example.lq.eyes.base.BasePresenter;
import com.example.lq.eyes.base.BaseView;
import com.example.lq.eyes.bean.DetailBean;
import com.example.lq.eyes.model.DetailModel;
import com.example.lq.eyes.view.DetailView;

/**
 * Created by 003 on 2019/5/19.
 */

public class DetailPresenter<V extends DetailView> extends BasePresenter<V> implements DetailModel.CallBack {
    private DetailModel mDetailModel = new DetailModel();

    public void getDetail() {
        mDetailModel.getFind(this);
    }

    @Override
    public void getFind(DetailBean bean) {
        mView.onSuccess(bean);
    }

    @Override
    public void getFail(String s) {
        mView.onFail(s);
    }
}
