package com.demo.dao;

import com.demo.model.LbsAddress;

import java.util.List;

/**
 * Created by hewei on 2018/11/14.
 */
public interface LbsAddressDao {

    public LbsAddress getLbsAddressById(Long id);

    public List<LbsAddress> getLbsAddress(int page);
}
