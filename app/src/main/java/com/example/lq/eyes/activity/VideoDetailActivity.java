package com.example.lq.eyes.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lq.eyes.R;
import com.example.lq.eyes.base.BaseApp;
import com.example.lq.eyes.widget.NetConnectedUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoDetailActivity extends AppCompatActivity {

    @BindView(R.id.find_back)
    ImageButton mFindBack;
    @BindView(R.id.video_toolbar_iv_right)
    ImageButton mVideoToolbarIvRight;
    @BindView(R.id.video_toolbar)
    Toolbar mVideoToolbar;
    @BindView(R.id.video_detail_iv)
    ImageView mVideoDetailIv;
    @BindView(R.id.video_paly)
    ImageView mVideoPaly;
    @BindView(R.id.video_detail_ivmo)
    ImageView mVideoDetailIvmo;
    @BindView(R.id.video_detail_title)
    TextView mVideoDetailTitle;
    @BindView(R.id.video_detail_time)
    TextView mVideoDetailTime;
    @BindView(R.id.video_detail_desc)
    TextView mVideoDetailDesc;
    @BindView(R.id.video_detail_iv_fav)
    ImageView mVideoDetailIvFav;
    @BindView(R.id.video_detail_tv_fav)
    TextView mVideoDetailTvFav;
    @BindView(R.id.video_detail_iv_share)
    ImageView mVideoDetailIvShare;
    @BindView(R.id.video_detail_tv_share)
    TextView mVideoDetailTvShare;
    @BindView(R.id.video_detail_ll_share)
    LinearLayout mVideoDetailLlShare;
    @BindView(R.id.video_detail_iv_reply)
    ImageView mVideoDetailIvReply;
    @BindView(R.id.video_detail_tv_reply)
    TextView mVideoDetailTvReply;
    @BindView(R.id.video_detail_iv_down)
    ImageView mVideoDetailIvDown;
    @BindView(R.id.video_detail_tv_down)
    TextView mVideoDetailTvDown;
    @BindView(R.id.video_detail_ll_down)
    LinearLayout mVideoDetailLlDown;
    private String feed;
    private String title;
    private String time;
    private String desc;
    private String blurred;
    private String video;
    private int collect;
    private int share;
    private int reply;
    private SharedPreferences mSharedPreferences = BaseApp.mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //获取数据
        //背景图片
        feed = getIntent().getStringExtra("feed");
        title = getIntent().getStringExtra("title");
        //时间
        time = getIntent().getStringExtra("time");
        //视频详情
        desc = getIntent().getStringExtra("desc");
        //模糊图片
        blurred = getIntent().getStringExtra("blurred");
        video = getIntent().getStringExtra("video");//视频播放地址
        //收藏量
        collect = getIntent().getIntExtra("collect", 0);
        //分享量
        share = getIntent().getIntExtra("share", 0);
        //回复量
        reply = getIntent().getIntExtra("reply", 0);
        //初始化界面数据
        Glide.with(this).load(feed).into(mVideoDetailIv);
        //mVideoDetailIv.setImageURI(Uri.parse(feed));
        mVideoDetailTitle.setText(title);
        mVideoDetailTime.setText(time);
        mVideoDetailDesc.setText(desc);
        Glide.with(this).load(blurred).into(mVideoDetailIvmo);
      //  mVideoDetailIvmo.setImageURI(Uri.parse(blurred));
        mVideoDetailTvFav.setText(String.valueOf(collect));
        mVideoDetailTvShare.setText(String.valueOf(share));
        mVideoDetailTvReply.setText(String.valueOf(reply));
    }

    @OnClick({R.id.video_toolbar_iv_right, R.id.find_back})
    public void onClick() {
        finish();
    }

    @OnClick({R.id.video_paly, R.id.video_detail_iv_fav, R.id.video_detail_ll_share, R.id.video_detail_iv_reply, R.id.video_detail_ll_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_paly:
                if (NetConnectedUtils.isNetConnected(this)) {
                        Intent intent = new Intent(this, VideoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("video", video);
                        bundle.putString("title", title);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    /*String userId = mSharedPreferences.getString("userId","000");
                    HistoryDetails historyDetails = new HistoryDetails(feed,title,time,getDate(),desc,blurred,video,collect,share,reply,userId);
                    List<HistoryDetails> temp = hdb.loadHistoryByTitle(title);//同步默认账户观看记录
                    List<HistoryDetails> temp2 = hdb.loadHistoryByUserId(userId);//同步userid观看记录
                    if (temp==null||temp2==null){
                        hdb.saveOrUpdate(historyDetails);
                    }else if (temp.size()==0||temp2.size()==0){
                        hdb.saveOrUpdate(historyDetails);
                    }

*/
                    //Log.e("------------","我在播放视频");

                } else {

                    break;
                }
        }
    }
}


