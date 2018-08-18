package com.zxy.mvn.service.impl;

import com.zxy.mvn.dataobject.SellerInfo;
import com.zxy.mvn.repository.SellerInfoRepository;
import com.zxy.mvn.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
