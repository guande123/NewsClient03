package com.gdcp.zgd.newsclient03.fragment;

import android.widget.ListView;

import com.gdcp.zgd.newsclient03.R;
import com.gdcp.zgd.newsclient03.adapter.NewsAdapter;
import com.gdcp.zgd.newsclient03.entity.NewsEntity;
import com.gdcp.zgd.newsclient03.entity.URLManager;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by yls on 2017/6/29.
 */

public class NewsFragment extends BaseFragment{

    /** 新闻类别id */
    private String channelId;
    private   ListView listView;
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
         listView = (ListView) mRoot.findViewById(R.id.lv_01);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        String url = URLManager.getUrl(channelId);

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                System.out.println("----服务器返回的json数据:" + json);

                json =  json.replace(channelId, "result");
                Gson gson = new Gson();
                NewsEntity newsDatas = gson.fromJson(json, NewsEntity.class);
                System.out.println("----解析json:" + newsDatas.getResult().size());

                // 显示数据到列表中
                showDatas(newsDatas);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
            }
        });
    }
    // 显示服务器数据
    private void showDatas(NewsEntity newsDatas) {
        if (newsDatas == null
                || newsDatas.getResult() == null
                || newsDatas.getResult().size() == 0) {
            System.out.println("----没有获取到服务器的新闻数据");
            return;
        }
        // （1）显示轮播图

                // （2）显示新闻列表
                NewsAdapter newsAdapter = new NewsAdapter(
                mActivity, newsDatas.getResult());
                listView.setAdapter(newsAdapter);

    }
}
