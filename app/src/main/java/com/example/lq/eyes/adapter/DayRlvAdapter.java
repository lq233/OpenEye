package com.example.lq.eyes.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lq.eyes.R;
import com.example.lq.eyes.activity.VideoDetailActivity;
import com.example.lq.eyes.bean.DayBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 003 on 2019/5/15.
 */

public class DayRlvAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final ArrayList<DayBean.IssueListEntity.ItemListEntity> mList;
    public static final int VIDEO = 1;
    public static final int TEXT = 2;


    public DayRlvAdapter(Context context, ArrayList<DayBean.IssueListEntity.ItemListEntity> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIDEO) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.main_item, null);
            return new Vh1(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.main_item2, null);
            return new Vh2(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);

        final DayBean.IssueListEntity.ItemListEntity.DataEntity data = mList.get(position).getData();
        String feed = "1";
        String title = "1";
        String category = "1";
        int duration = 0;
        String text = "1";


        if (type == VIDEO) {
            Vh1 holder1 = (Vh1) holder;
            //获取相关信息
            feed = data.getCover().getFeed();
            title = data.getTitle();
            category = data.getCategory();
            category = "#" + category + "  /  ";
            duration = mList.get(position).getData().getDuration();

            int last = duration % 60;
            String stringLast;
            if (last <= 9) {
                stringLast = "0" + last;
            } else {
                stringLast = last + "";
            }

            //获取视频时间
            String durationString;
            int minit = duration / 60;
            if (minit < 10) {
                durationString = "0" + minit;

            } else {

                durationString = "" + minit;

            }
            String stringTime = durationString + "' " + stringLast + '"';
//            Uri uri = Uri.parse(feed);
            Glide.with(mContext).load(feed).into(holder1.mImg);
//            holder1.mImg.setImageURI(uri);
            holder1.mTv_title.setText(title);
            holder1.mTv_time.setText(category + stringTime);
            final String finalTitle = title;
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VideoDetailActivity.class);
                    //获取标题
                    intent.putExtra("title", finalTitle);
                    //获取视频时间
                    int duration = data.getDuration();
                    int mm = duration / 60;
                    int ss = duration % 60;
                    String second = "";//秒
                    String minute = "";//分
                    if (ss < 10) {
                        second = "0" + String.valueOf(ss);
                    } else {
                        second = String.valueOf(ss);
                    }
                    if (mm < 10) {
                        minute = "0" + String.valueOf(mm);
                    } else {
                        minute = String.valueOf(mm);//分钟
                    }

                    intent.putExtra("time", "#" + data.getCategory() + " / " + minute + "'" + second + '"');
                    intent.putExtra("desc", data.getDescription());//视频描述
                    intent.putExtra("blurred", data.getCover().getBlurred());//模糊图片地址
                    intent.putExtra("feed", data.getCover().getFeed());//图片地址
                    intent.putExtra("video", data.getPlayUrl());//视频播放地址
                    intent.putExtra("collect", data.getConsumption().getCollectionCount());//收藏量
                    intent.putExtra("share", data.getConsumption().getShareCount());//分享量
                    intent.putExtra("reply", data.getConsumption().getReplyCount());//回复数量
                    mContext.startActivity(intent);

                }
            });

        } else {
            Vh2 holder2 = (Vh2) holder;
            String s = data.getText();
            holder2.mTv_text.setText(s);

            String image = data.getImage();
            if (!TextUtils.isEmpty(image)) {
                holder2.mTv_text.setTextSize(20);
                holder2.mTv_text.setText("-Weekend  special-");
            }
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<DayBean.IssueListEntity.ItemListEntity> itemList) {
        mList.addAll(itemList);
        notifyDataSetChanged();
    }

    class Vh1 extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTv_title;
        private final TextView mTv_time;

        public Vh1(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.iv);
            mTv_title = itemView.findViewById(R.id.tv_title);
            mTv_time = itemView.findViewById(R.id.tv_time);
        }
    }

    class Vh2 extends RecyclerView.ViewHolder {

        private final TextView mTv_text;

        public Vh2(View itemView) {
            super(itemView);
            mTv_text = itemView.findViewById(R.id.tv_home_text);
        }
    }

    /*
 * 为item设置不同的数据和布局
 * */
    @Override
    public int getItemViewType(int position) {
        DayBean.IssueListEntity.ItemListEntity itemListBean = mList.get(position);
        if ("video".equals(itemListBean.getType())) {
            return VIDEO;
        }
        return TEXT;
    }

}
