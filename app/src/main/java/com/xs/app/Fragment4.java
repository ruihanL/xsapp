package com.xs.app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.xs.adapter.NewsListAdapter;
import com.xs.model.NewsItem;
import com.xs.utils.ServerUtil;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;

public class Fragment4 extends Fragment implements XListView.IXListViewListener {

    private View mView;

    //列表相关
    private XListView listview;
    private NewsListAdapter ma;
    private NewsItem mm;
    private List<NewsItem> mms = new ArrayList<>();
    private long lateTime = 0;//标识加载更多的时间


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.mainpage4, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化列表的配置
        listview = (XListView)getActivity().findViewById(R.id.listView1);
        listview.setPullLoadEnable(true);
        listview.setPullRefreshEnable(true);
        listview.setXListViewListener(this);
        ma = new NewsListAdapter(getActivity(), mms);
        listview.setAdapter(ma);

        onRefresh();
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        lateTime = 0;
        mms.clear();
        getNewsFromServer();//获取数据
    }

    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub
        getNewsFromServer();
    }

    private void getNewsFromServer()
    {
        final List<NewsItem> lists = new ArrayList<>();
        final AsyncHttpClient client = new AsyncHttpClient();
        try {
            client.post(ServerUtil.NEWS_ADDR, ServerUtil.getNewsParams(lateTime), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

                }

                @Override
                public void onSuccess(int i, Header[] headers, String s) {
                    try {
                        listview.stopRefresh();
                        listview.stopLoadMore();
                        listview.setRefreshTime(new Date().toLocaleString());

                        List<NewsItem> newsItems = ServerUtil.getNewsRet(s);
                        if (newsItems==null) throw new Exception("list is null");

                        for (NewsItem item :newsItems)
                        {
                            mms.add(item);
                            lateTime = item.getBehot_time();
                        }
                        ma.notifyDataSetChanged();

                    }catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
