package com.demo.service.impl;

import com.demo.dao.AddressWebDao;
import com.demo.model.AddressWeb;
import com.demo.service.AddressWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hewei on 2018/11/12.
 */
@Service
public class AddressWebServiceImpl implements AddressWebService {

    @Autowired
    private AddressWebDao addressWebDao;

    @Override
    public void addAddressWeb(AddressWeb addressWeb) {
        addressWebDao.addAddressWeb(addressWeb);
    }
}
