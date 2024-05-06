package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.PageResult;
import com.vky.entity.QueryPageBean;
import com.vky.entity.Result;
import com.vky.pojo.Competition;
import com.vky.service.CompetitionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Reference
    private CompetitionService competitionService;

    //TODO 新增操作
    @RequestMapping("/add.do")
    public Result add(@RequestBody Competition competition){
        return null;
    }

    //TODO 分页查询
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return null;
    }

    //TODO 删除操作
    @RequestMapping("/delete.do")
    public Result delete(Integer id){
        return null;
    }

    //TODO 编辑操作
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody Competition competition){
        return null;
    }


}
