package com.example.lq.eyes.view;

import com.example.lq.eyes.bean.DetailBean;

/**
 * Created by 003 on 2019/5/19.
 */

public interface DetailView {
    void onSuccess(DetailBean bean);
    void onFail(String s);
}
