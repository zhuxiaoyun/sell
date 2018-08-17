package com.zxy.mvn.controller;

import com.zxy.mvn.dataobject.ProductCategory;
import com.zxy.mvn.exception.MvnException;
import com.zxy.mvn.form.CategoryForm;
import com.zxy.mvn.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端类目
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/category/list", map);
    }

    /**
     * 新增/修改
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {

        if (categoryId != null) {
            ProductCategory category = categoryService.findOne(categoryId);
            map.put("category", category);
        }
        return new ModelAndView("/category/index", map);
    }

    /**
     * 保存
     * @param form
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                              BindingResult bindingResult,
                              Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("/common/error", map);
        }

        ProductCategory category = new ProductCategory();
        try {
            //categoryId为空，即新增
            if (form.getCategoryId() != null) {
                category = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, category);
            categoryService.save(category);
        } catch (MvnException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("/common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("/common/success", map);
    }

}
