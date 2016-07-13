package com.xs.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.xs.model.NewsItem;
import com.xs.utils.ServerUtil;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        getNewsFromServer();

    }

    private void getNewsFromServer()
    {
        final List<NewsItem> lists = new ArrayList<>();
        final AsyncHttpClient client = new AsyncHttpClient();
        try {
            client.post(ServerUtil.NEWS_ADDR, ServerUtil.getNewsParams(0), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

                }

                @Override
                public void onSuccess(int i, Header[] headers, String s) {
                    try {
                        List<NewsItem> newsItems = ServerUtil.getNewsRet(s);
                        if (newsItems==null) throw new Exception("list is null");

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
