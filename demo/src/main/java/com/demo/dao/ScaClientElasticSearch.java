package com.demo.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.config.EsUtils;
import com.demo.model.ScaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  liujian on 2018/11/23.
 */
@Component
public class ScaClientElasticSearch {

    @Autowired
    private EsUtils esUtils;

    public void deleteScaClient() {
        String url = "sca-client/sca-client/_delete_by_query";
        String json="{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}";
        esUtils.post(url,json);
    }

    public void bulkScaClient(List<ScaClient> scaClientList) {
        String url = "_bulk";
        String json = "";
        for(ScaClient scaClient:scaClientList){
            json+="{ \"index\" : { \"_index\" : \"sca-client\", \"_type\" : \"sca-client\", \"_id\" : \""+scaClient.getId()+"\" } }\n";
            json+= JSONObject.toJSONString(scaClient)+"\n";
        }
        JSONObject result = esUtils.post(url,json);
    }


}
