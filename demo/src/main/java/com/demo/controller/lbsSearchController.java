package com.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.config.EsUtils;
import com.demo.model.LbsAddress;
import com.demo.model.LbsSearchReq;
import com.demo.model.LbsSearchRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hewei on 2018/11/15.
 */
@RestController
@RequestMapping("/lbsSearch")
public class lbsSearchController {

    @Autowired
    private EsUtils esUtils;

    @GetMapping("/")
    public List<LbsSearchRes> getLbsSearch(@ModelAttribute LbsSearchReq lbsSearchReq){
        List<LbsSearchRes> lbsSearchResList = new ArrayList<>();
        String productName = lbsSearchReq.getProductName();
        Date startTime = lbsSearchReq.getStartTime();
        Date endTime = lbsSearchReq.getEndTime();
        Long province = lbsSearchReq.getProvince();
        Long city = lbsSearchReq.getCity();
        Long district = lbsSearchReq.getDistrict();
        String url = "lbs-search-v1/_search";
        String json = "";

         json += "{\n" +
                "  \"size\": 0, \n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"must\": [\n" +
                "          {\n" +
                "            \"term\": {\n" +
                "              \"province\": {\n" +
                "                \"value\": \""+province+"\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" ;
                if(city!=null){
                    json+= "          ,{\n" +
                            "            \"term\": {\n" +
                            "              \"city\": {\n" +
                            "                \"value\": \""+city+"\"\n" +
                            "              }\n" +
                            "            }\n" +
                            "          }\n";
                }
                if(district!=null){
                    json+= "          ,{\n" +
                            "            \"term\": {\n" +
                            "              \"city\": {\n" +
                            "                \"value\": \""+district+"\"\n" +
                            "              }\n" +
                            "            }\n" +
                            "          }\n";
                }
               json+= "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"aggs\": {\n" +
                "      \"group_by_addressId\": {\n" +
                "          \"terms\": {\n" +
                "              \"field\": \"addressId\",\n" +
                "              \"size\": \"10000\"\n" +
                "          },\n" +
                "          \"aggs\": {\n" +
                "              \"top_addressId_hits\": {\n" +
                "                  \"top_hits\": {\n" +
                "                      \"sort\": [\n" +
                "                          {\n" +
                "                              \"addressId\": {\n" +
                "                                  \"order\": \"desc\"\n" +
                "                              }\n" +
                "                          }\n" +
                "                      ],\n" +
                "                      \"_source\": {\n" +
                "                          \"includes\": [ \"province\",\"address\", \"latitude\",\"longitude\",\"createdTime\",\"productName\",\"addressId\"]\n" +
                "                      },\n" +
                "                      \"size\" : 1\n" +
                "                  }\n" +
                "              }\n" +
                "          }\n" +
                "      }\n" +
                "  }\n" +
                "}";
        JSONObject result = esUtils.get(url,json);
        JSONArray buckets = result.getJSONObject("aggregations").getJSONObject("group_by_addressId").getJSONArray("buckets");
        for(int i=0;i<buckets.size();i++){
            JSONObject jsonObject = (JSONObject)buckets.get(i);
            JSONArray hits = jsonObject.getJSONObject("top_addressId_hits").getJSONObject("hits").getJSONArray("hits");
            JSONObject source = ((JSONObject)hits.get(0)).getJSONObject("_source");
            LbsSearchRes lbsSearchRes = JSONObject.parseObject(source.toJSONString(),LbsSearchRes.class);
            lbsSearchResList.add(lbsSearchRes);
        }
        return lbsSearchResList;
    }
}
