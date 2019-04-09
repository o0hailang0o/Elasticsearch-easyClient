package com.demo.dao;

import com.demo.model.Column;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liujian on 2019/1/9.
 */
public interface MysqlDao {

    /**
     * 查询数据库 所有的 表名
     * @param baseName
     * @return
     */
    List<String> listTableName(@Param("baseName") String baseName);

    List<Column> getFieldList(@Param("tableName") String tableName, @Param("baseName") String baseName);

    /**
     * 查询表数据
     * @param tableName
     * @return
     */
    List<Map<String,Object>> getDatas(@Param("tableName") String tableName,@Param("minId")Long minId,@Param("maxId")Long maxId);

    Long minId(@Param("tableName") String tableName);

    Long maxId(@Param("tableName") String tableName);
}
