package com.xs.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.xs.model.NewsItem;
import com.xs.model.RespRet;

import java.util.Date;
import java.util.List;

/**
 * Created by linrh on 2016/6/2.
 */
public class ServerUtil {

    //请求的地址
    public static String NEWS_ADDR  = "http://api.1-blog.com/biz/bizserver/news/list.do";

    //构建参数
    public static RequestParams getNewsParams(long t)
    {
        RequestParams requestParams = new RequestParams();
        requestParams.add("max_behot_time", t>0?t+"":(new Date()).getTime()+"");//时间之前
        requestParams.add("size","30"); //获取30条数据
        return requestParams;
    }

    //解析返回的json结果
    public static List<NewsItem> getNewsRet(String json){
        LogUtil.show(json);
        Gson gson = new Gson();
        RespRet ret = null;
        try{
            ret = gson.fromJson(json,new TypeToken<RespRet>(){}.getType());
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ret.getDetail();
    }

}


