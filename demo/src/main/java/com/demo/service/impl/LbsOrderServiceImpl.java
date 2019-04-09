package com.demo.service.impl;

import com.demo.dao.LbsOrderDao;
import com.demo.model.LbsAddress;
import com.demo.model.LbsOrder;
import com.demo.service.LbsAddressService;
import com.demo.service.LbsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hewei on 2018/11/14.
 */
@Service
public class LbsOrderServiceImpl implements LbsOrderService {

    @Autowired
    private LbsOrderDao lbsOrderDao;

    @Override
    public LbsOrder getLbsOrderById(Long id) {
        return lbsOrderDao.getLbsOrderById(id);
    }
}
