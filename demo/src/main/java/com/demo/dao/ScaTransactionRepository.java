package com.demo.dao;

import com.demo.model.ScaTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujian on 2018/11/24.
 */
@Repository
public class ScaTransactionRepository {

    @Autowired
    private ScaTransactionElasticSearch scaTransactionElasticSearch;
    @Autowired
    private ScaTransactionMapper scaTransactionMapper;

    public void deleteScaTransaction() {
        scaTransactionElasticSearch.deleteScaTransaction();
    }

    public Integer countScaTransaction() {
        return scaTransactionMapper.countScaTransaction();
    }


    public void bulkScaTransaction(List<ScaTransaction> scaTransactionList) {
        scaTransactionElasticSearch.bulkScaTransaction(scaTransactionList);
    }

    public List<ScaTransaction> findAll(Integer pageNum, int pageSize) {
        return scaTransactionMapper.findAll((pageNum-1)*pageSize,(pageNum-1)*pageSize+pageSize);
    }
}
