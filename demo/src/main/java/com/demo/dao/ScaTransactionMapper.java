package com.demo.dao;

import com.demo.model.ScaTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liujian on 2018/11/24.
 */
@Mapper
public interface ScaTransactionMapper{

    Integer countScaTransaction();

    List<ScaTransaction> findAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
