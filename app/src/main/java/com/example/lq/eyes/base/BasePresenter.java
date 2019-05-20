package com.example.lq.eyes.base;

import java.lang.ref.WeakReference;

/**
 * Created by 003 on 2019/5/15.
 */

public class BasePresenter<V> {
    private WeakReference<V> mWeakReference;
    public V mView;

    public void attachView(V v) {
        mWeakReference = new WeakReference<>(v);
        mView=mWeakReference.get();
    }
    public void deachView(){
        if (mWeakReference != null) {
            mWeakReference.clear();
        }
    }
}
