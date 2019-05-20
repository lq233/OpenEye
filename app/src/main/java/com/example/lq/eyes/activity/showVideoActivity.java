package com.example.lq.eyes.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lq.eyes.R;
import com.example.lq.eyes.widget.CustomMediaController;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class showVideoActivity extends AppCompatActivity implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private VideoView mBuffer;
    private ProgressBar mProbar;
    /**  */
    private TextView mDownloadRate;
    /**  */
    private TextView mLoadRate;
    private MediaController mMediaController;
    private CustomMediaController mCustomMediaController;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = showVideoActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //初始化加载文件
        Vitamio.initialize(this);
        setContentView(R.layout.activity_show_video);
        initView();
        initData();
    }

    private void initData() {
        String video = getIntent().getStringExtra("video");//视频播放地址
        String title = getIntent().getStringExtra("title");//视频标题
        mCustomMediaController.setVideoName(title);
        if (getIntent().getBooleanExtra("download", false)) {
            mBuffer.setVideoPath(video);
        } else {
            uri = Uri.parse(video);
            mBuffer.setVideoURI(uri);//设置视频播放地址
        }

        mBuffer.setMediaController(mCustomMediaController);//设置视频控制器
        mBuffer.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mMediaController.show(5000);//控制器显示5秒
        mBuffer.requestFocus();
        mBuffer.setOnInfoListener(this);//设置警告信息监听
        mBuffer.setOnBufferingUpdateListener(this);//设置媒体缓存状态监听
        /*
        * 加载完毕准备播放
        * */
        mBuffer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);//播放速度
            }
        });
    }

    private void initView() {
        mBuffer = (VideoView) findViewById(R.id.buffer);
        mMediaController = new MediaController(this);
        mCustomMediaController = new CustomMediaController(this, mBuffer, this);
        mProbar = (ProgressBar) findViewById(R.id.probar);
        mDownloadRate = (TextView) findViewById(R.id.download_rate);
        mLoadRate = (TextView) findViewById(R.id.load_rate);
    }

    /*
*信息或警告。
* */
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START://暂停播放,缓存数据
                if (mBuffer.isPlaying()) {
                    mBuffer.pause();
                    mProbar.setVisibility(View.VISIBLE);
                    mDownloadRate.setText("");
                    mLoadRate.setText("");
                    mDownloadRate.setVisibility(View.VISIBLE);
                    mLoadRate.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END://缓冲完成开始播放
                mBuffer.start();
                mProbar.setVisibility(View.GONE);
                mDownloadRate.setVisibility(View.GONE);
                mLoadRate.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED://缓冲速度
                mDownloadRate.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    /*
    * 更新流媒体缓存状态。
    * */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        mLoadRate.setText(percent + "%");
    }

    /*
    * 屏幕切换时设置全屏
    * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mBuffer != null) {
            mBuffer.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

}
