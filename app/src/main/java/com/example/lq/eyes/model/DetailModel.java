package com.example.lq.eyes.model;

import com.example.lq.eyes.bean.DetailBean;
import com.example.lq.eyes.network.MyServer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 003 on 2019/5/19.
 */

public class DetailModel {
    public interface CallBack {
        void getFind(DetailBean bean);

        void getFail(String s);
    }

    public void getFind(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.url2).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        MyServer server = retrofit.create(MyServer.class);
        Observable<DetailBean> observable = server.getDetail();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailBean bean) {
                        callBack.getFind(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
