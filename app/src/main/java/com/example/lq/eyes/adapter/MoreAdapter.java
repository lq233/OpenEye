package com.example.lq.eyes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lq.eyes.R;
import com.example.lq.eyes.activity.FinddetailActivity;
import com.example.lq.eyes.bean.MoreBean;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by 003 on 2019/5/17.
 */

public class MoreAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    private final ArrayList<MoreBean> mList;

    public MoreAdapter(Context context, ArrayList<MoreBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.more_item, null);
        return new Vh(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Vh holder1 = (Vh) holder;
        Glide.with(mContext).load(mList.get(position).getBgPicture()).into(holder1.mImg);
        holder1.mTv.setText(mList.get(position).getName());

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FinddetailActivity.class);
                intent.putExtra("title", mList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<MoreBean> bean) {
        mList.addAll(bean);
        notifyDataSetChanged();
    }

    class Vh extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTv;

        public Vh(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.more_img);
            mTv = itemView.findViewById(R.id.more_tv);
        }
    }
}
