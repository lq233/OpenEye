package com.example.lq.eyes.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lq.eyes.R;
import com.example.lq.eyes.adapter.DayRlvAdapter;
import com.example.lq.eyes.bean.DayBean;
import com.example.lq.eyes.network.MyServer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
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
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment {


    private View view;
    private RecyclerView mDailyRlv;
    private SmartRefreshLayout mDailySmart;
    private ArrayList<DayBean.IssueListEntity.ItemListEntity> mList;
    private DayRlvAdapter mAdapter;

    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_day, container, false);
        initView(inflate);
        initData();
        return inflate;
    }


    private void initView(View inflate) {
        mDailyRlv = (RecyclerView) inflate.findViewById(R.id.daily_rlv);
        mDailySmart = (SmartRefreshLayout) inflate.findViewById(R.id.daily_smart);
        mDailyRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mAdapter = new DayRlvAdapter(getContext(), mList);
        mDailyRlv.setAdapter(mAdapter);


    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(MyServer.url).build();
        MyServer server = retrofit.create(MyServer.class);
        Observable<DayBean> observable = server.getDay();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DayBean dayBean) {
                        List<DayBean.IssueListEntity> list = dayBean.getIssueList();
                        int a = list.size();

                        mAdapter.addData(dayBean.getIssueList().get(0).getItemList());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
