package com.demo.dao;

import com.demo.model.LbsOrderItem;

import java.util.List;

/**
 * Created by hewei on 2018/11/14.
 */
public interface LbsOrderItemDao {

    public List<LbsOrderItem> listLbsOrderItem(int page);
}
