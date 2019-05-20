package com.example.lq.eyes.activity;

import android.content.Intent;
import android.mtp.MtpConstants;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.lq.eyes.R;
import com.example.lq.eyes.adapter.FindVpAdapter;
import com.example.lq.eyes.fragment.FinddetailFragment;

import java.util.ArrayList;

public class FinddetailActivity extends AppCompatActivity {

    private TabLayout mFindTab;
    private ViewPager mFindVp;
    private TextView mFindTitle;
    private Toolbar mFindToolbar;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finddetail);
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        initView();
    }

    private void initView() {
        mFindTab = (TabLayout) findViewById(R.id.find_tab);
        mFindVp = (ViewPager) findViewById(R.id.find_vp);
        mFindTitle = (TextView) findViewById(R.id.find_title);
        mFindToolbar = (Toolbar) findViewById(R.id.find_toolbar);
        mFindToolbar.setTitle("");
        setSupportActionBar(mFindToolbar);
        mFindTitle.setText(mTitle);
        ArrayList<String> title = new ArrayList<>();
        title.add("按时间排序");
        title.add("分享排行");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FinddetailFragment(mTitle));
        fragments.add(new FinddetailFragment(mTitle));
        FindVpAdapter adapter = new FindVpAdapter(getSupportFragmentManager(), title, fragments);
        mFindVp.setAdapter(adapter);
        mFindTab.setupWithViewPager(mFindVp);

    }
}
