package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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
    public Result add(@RequestBody Competition competition){
        return null;
    }


}
