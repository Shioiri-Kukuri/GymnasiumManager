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
        try {
            competitionService.save(competition);
        }catch (Exception e){
            //新增失败
            return new Result(false, "新增失败");
        }
        //新增成功
        return new Result(true,"新增成功");


    }

    //TODO 分页查询
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = competitionService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        Competition competition = competitionService.getById(id);
        if(competition != null){
            Result result = new Result(true, "分页查询成功执行");
            result.setData(competition);
            return result;
        }
        return new Result(false,"分页查询执行失败");
    }

    //TODO 删除操作
    @RequestMapping("/delete.do")
    public Result delete(Integer id){
        try {
            competitionService.deleteById(id);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    //TODO 编辑操作
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody Competition competition){
        try {
           competitionService.edit(competition);
        }catch (Exception e){
            return new Result(false,"编辑失败");
        }
        return new Result(true,"编辑成功");
    }


}
