package com.demo.service.impl;

import com.demo.dao.LbsAddressDao;
import com.demo.model.LbsAddress;
import com.demo.service.LbsAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hewei on 2018/11/14.
 */
@Service
public class LbsAddressServiceImpl implements LbsAddressService {

    @Autowired
    private LbsAddressDao lbsAddressDao;

    @Override
    public LbsAddress getLbsAddressById(Long id) {
        return lbsAddressDao.getLbsAddressById(id);
    }

    @Override
    public List<LbsAddress> getLbsAddress(int page) {
        return lbsAddressDao.getLbsAddress(page);
    }
}
