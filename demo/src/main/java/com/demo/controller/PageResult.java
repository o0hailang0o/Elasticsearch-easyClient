package com.demo.controller;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujian on 2019/1/21.
 */
public class PageResult {

    private List<JSONObject> jsonObjectList = new ArrayList<>();

    /**
     * count 在聚合查询中 超过10000条 只能10000
     */
    private Integer count;

    public List<JSONObject> getJsonObjectList() {
        return jsonObjectList;
    }

    public void setJsonObjectList(List<JSONObject> jsonObjectList) {
        this.jsonObjectList = jsonObjectList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "jsonObjectList=" + jsonObjectList +
                ", count=" + count +
                '}';
    }
}
