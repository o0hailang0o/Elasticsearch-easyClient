package com.demo.dao;

import com.demo.model.ScaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujian on 2018/11/23.
 */
@Repository
public class ScaClientRepository {

    @Autowired
    private ScaClientElasticSearch scaClientElasticSearch;
    @Autowired
    private ScaClientMapper scaClientMapper;

    public void deleteScaClient() {
        scaClientElasticSearch.deleteScaClient();
    }

    public Integer countScaClient() {
        return scaClientMapper.countScaClient();
    }

    public void bulkScaClient(List<ScaClient> lbsClientList) {
        scaClientElasticSearch.bulkScaClient(lbsClientList);
    }

    public List<ScaClient> getScaClientList(Integer pageNum, int pageSize) {
        return scaClientMapper.getScaClientList((pageNum-1)*pageSize,(pageNum-1)*pageSize+pageSize);
    }
}
