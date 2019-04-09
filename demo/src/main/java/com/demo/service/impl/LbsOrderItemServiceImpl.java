package com.demo.service.impl;

import com.demo.dao.LbsOrderItemDao;
import com.demo.model.LbsOrderItem;
import com.demo.service.LbsOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hewei on 2018/11/14.
 */
@Service
public class LbsOrderItemServiceImpl implements LbsOrderItemService {

    @Autowired
    private LbsOrderItemDao LbsOrderItemDao;

    @Override
    public List<LbsOrderItem> listLbsOrderItem(int page) {
        return LbsOrderItemDao.listLbsOrderItem(page);
    }
}
