package com.zxy.mvn.service;

import com.zxy.mvn.dataobject.SellerInfo;

/**
 * 卖家端
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
