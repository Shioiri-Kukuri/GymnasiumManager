package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.PageResult;
import com.vky.entity.QueryPageBean;
import com.vky.entity.Result;
import com.vky.pojo.Field;
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

//    场地新增
    @RequestMapping("/add.do")
    public Result add(@RequestBody Field field){
        try{
            fieldService.add(field);
        }catch(Exception e){
            return new Result(false,"新增失败");
        }
        return new Result(true,"新增成功");
    }

    //根据id查询场地
    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        Field field = fieldService.findById(id);
        if(field != null){
            Result result = new Result(true,"根据id查询成功");
            result.setData(field);
            return result;
        }
        return new Result(false,"根据id查询失败");
    }

    @RequestMapping("/edit.do")
    public Result edit(@RequestBody Field field){
        try{
            fieldService.edit(field);
        }catch(Exception e){
            return new Result(false,"修改失败");
        }
        return new Result(true,"修改成功");
    }
}
