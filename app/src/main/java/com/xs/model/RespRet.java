package com.xs.model;

import java.util.List;

/**
 * Created by linrh on 2016/6/3.
 */

public class RespRet {
    //根据返回的json字符串的格式创建一个对象，里面有数组的，就用list
    private String status;
    private String desc;
    private List<NewsItem> detail;

    public List<NewsItem> getDetail() {
        return detail;
    }
}
