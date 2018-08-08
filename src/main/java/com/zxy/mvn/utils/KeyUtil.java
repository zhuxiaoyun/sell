package com.zxy.mvn.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式 当前时间+六位随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
