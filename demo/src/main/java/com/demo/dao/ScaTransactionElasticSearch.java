package com.demo.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.config.EsUtils;
import com.demo.model.ScaTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author liujian on 2018/11/24.
 */
@Repository
public class ScaTransactionElasticSearch {

    @Autowired
    private EsUtils esUtils;

    public void deleteScaTransaction() {
        String url = "/sca-transaction/sca-transaction/_delete_by_query";
        String json="{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}";
        esUtils.post(url,json);
    }

    public void bulkScaTransaction(List<ScaTransaction> scaScaTransactionList) {
        String url = "_bulk";
        String json = "";
        for(ScaTransaction scaTransaction:scaScaTransactionList){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            json += "{ \"index\" : { \"_index\" : \"sca-transaction\", \"_type\" : \"sca-transaction\", \"_id\" : \"" + scaTransaction.getId() + "\" } }\n";
            JSONObject jsonObj = (JSONObject) JSON.toJSON(scaTransaction);
            jsonObj.put("transactionTime",sdf.format(scaTransaction.getTransactionTime()));
            json += jsonObj.toJSONString() + "\n";
        }
         JSONObject result = esUtils.post(url, json);
    }

}
