package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.PageResult;
import com.vky.entity.QueryPageBean;
import com.vky.pojo.FieldAppointment;
import com.vky.service.FieldAppointmentService;
import com.vky.service.FieldService;
import com.vky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class FieldAppointmentController {
    @Reference
    private FieldAppointmentService fieldAppointmentService;
    @Reference
    private UserService userService;
    @RequestMapping("/findPage.do")
    public PageResult findAppointmentPage(@RequestBody QueryPageBean queryPageBean){

        PageResult pageResult = fieldAppointmentService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }
}
