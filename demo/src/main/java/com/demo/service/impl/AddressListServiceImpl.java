package com.demo.service.impl;

import com.demo.dao.AddressListDao;
import com.demo.model.AddressList;
import com.demo.service.AddressListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hewei on 2018/11/9.
 */
@Service
public class AddressListServiceImpl implements AddressListService {

    @Autowired
    private AddressListDao addressListDao;

    @Override
    public void addAddressList(AddressList addressList) {
        addressListDao.addAddressList(addressList);
    }

    @Override
    public List<AddressList> getAddressList(){
        return addressListDao.getAddressList();
    }

    @Override
    public void updateStatus(Long id) {
        addressListDao.updateStatus(id);
    }
}
