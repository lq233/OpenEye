package com.example.lq.eyes.network;

import com.example.lq.eyes.bean.DayBean;
import com.example.lq.eyes.bean.DetailBean;
import com.example.lq.eyes.bean.MoreBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 003 on 2019/5/15.
 */

public interface MyServer {
    public String url = "http://baobab.wandoujia.com/api/";

    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<DayBean> getDay();
    //http://baobab.wandoujia.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";

    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Observable<List<MoreBean>> getMore();

    //http://baobab.kaiyanapp.com/api/v4/discovery/category
    public String url2 = "http://baobab.kaiyanapp.com/api/";

    @GET("v4/discovery/category")
    Observable<DetailBean> getDetail();

}
