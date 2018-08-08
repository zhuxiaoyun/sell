package com.zxy.mvn.controller;

import com.zxy.mvn.domain.Girl;
import com.zxy.mvn.domain.Result;
import com.zxy.mvn.repository.GirlRepository;
import com.zxy.mvn.service.GirlService;
import com.zxy.mvn.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> list() {
        logger.info("cccc");
        return girlRepository.findAll();
    }

    /**
     * 新增一个女生
     * @param girl
     * @return
     */
    @PostMapping(value = "/girls")
    public Result<Girl> create(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 查询一个女生
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Girl getOne(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);
    }

    /**
     * 删除一个女生
     */
    @DeleteMapping(value = "/girls/{id}")
    public void delete(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    /**
     * 修改一个女生
     */
    @PutMapping(value = "/girls/{id}")
    public Girl put(@PathVariable("id") Integer id,
                    @RequestParam("cupSize") String cupSize,
                    @RequestParam("age") Integer age) {
        Girl girl = girlRepository.getOne(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 根据年龄查询
     * @param age
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void save() {
        girlService.insertTwo();
    }

    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }
}
