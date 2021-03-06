package com.demo.service;

import com.demo.model.AddressList;

import java.util.List;

/**
 * Created by hewei on 2018/11/9.
 */
public interface AddressListService {

    public void addAddressList(AddressList addressList);

    public List<AddressList> getAddressList();

    public void updateStatus(Long id);
}
