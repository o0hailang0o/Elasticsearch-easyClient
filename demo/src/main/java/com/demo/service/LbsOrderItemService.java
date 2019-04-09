package com.demo.service;

import com.demo.model.LbsOrderItem;

import java.util.List;

/**
 * Created by hewei on 2018/11/14.
 */
public interface LbsOrderItemService {

    public List<LbsOrderItem> listLbsOrderItem(int page);
}
