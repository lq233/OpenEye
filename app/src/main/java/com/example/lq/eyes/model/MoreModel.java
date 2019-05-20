package com.example.lq.eyes.model;

import android.util.Log;

import com.example.lq.eyes.bean.MoreBean;
import com.example.lq.eyes.network.API;
import com.example.lq.eyes.network.MyServer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 003 on 2019/5/17.
 */

public class MoreModel {

    private static final String TAG = "MoreModel";
    public interface CallBack {
        void getData(List<MoreBean> moreBean);
    }

    public void getMore(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<List<MoreBean>> observable = myServer.getMore();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<MoreBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MoreBean> moreBean) {
                        callBack.getData(moreBean);
                        Log.d(TAG, "onNext: "+moreBean.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
