package com.zxy.mvn.service;

import com.zxy.mvn.domain.Girl;
import com.zxy.mvn.enums.ResultEnum;
import com.zxy.mvn.exception.GirlException;
import com.zxy.mvn.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo() {
        Girl girl1 = new Girl();
        girl1.setCupSize("A");
        girl1.setAge(19);
        girlRepository.save(girl1);

        Girl girl2 = new Girl();
        girl2.setCupSize("BB");
        girl2.setAge(20);
        girlRepository.save(girl2);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age <= 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age <= 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    public Girl findOne(Integer id) {
        return girlRepository.findOne(id);
    }
}
