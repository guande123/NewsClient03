package com.gdcp.zgd.newsclient03.fragment;

import android.widget.TextView;

import com.gdcp.zgd.newsclient03.R;

/**
 * Created by yls on 2017/6/29.
 */

public class NewsFragment extends BaseFragment{

    /** 新闻类别id */
    private String channelId;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        TextView textView = (TextView) mRoot.findViewById(R.id.tv_01);
        textView.setText(channelId);    // 显示新闻类别id，以作区分
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
    }
}
