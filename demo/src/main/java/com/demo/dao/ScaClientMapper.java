package com.demo.dao;

import com.demo.model.ScaClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  liujian on 2018/11/23.
 */
@Mapper
public interface ScaClientMapper {

    Integer countScaClient();

    List<ScaClient> getScaClientList(@Param("pageNum") Integer pageNum, @Param("pageSize") int pageSize);
}
