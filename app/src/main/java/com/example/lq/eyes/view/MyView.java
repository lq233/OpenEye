package com.example.lq.eyes.view;

import com.example.lq.eyes.base.BaseView;
import com.example.lq.eyes.bean.MoreBean;

import java.util.List;

/**
 * Created by 003 on 2019/5/17.
 */

public interface MyView extends BaseView{
    void onSuccess(List<MoreBean> bean);

    void onFail(String s);
}
