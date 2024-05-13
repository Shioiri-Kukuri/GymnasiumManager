package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.PageResult;
import com.vky.entity.QueryPageBean;
import com.vky.service.FieldService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/field")
public class FieldController {

    @Reference
    private FieldService fieldService;

    //分页查询
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = fieldService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }
}
