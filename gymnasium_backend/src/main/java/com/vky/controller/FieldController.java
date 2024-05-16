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

import java.util.List;

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

//    根据空闲状态查询
    @RequestMapping("/findByStatus.do")
    public Result findByStatus(Integer status){
        List<Field> fieldList = fieldService.findByStatus(status);
        if (fieldList != null && fieldList.size() > 0){
            Result result = new Result(true,"根据status查询成功");
            result.setData(fieldList);
            return result;
        }
        return new Result(false,"根据status查询失败");
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

    /**
     * 删除场地
     */
    @RequestMapping("/delete.do")
    public Result delete(Integer id){
        try{
            fieldService.delete(id);
        }catch(Exception e){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    /**
     * 根据场地名称查询场地信息
     * @param name
     * @return
     */
    @RequestMapping("/findByName.do")
    public Result findByName(String name){
        Field field = fieldService.findByName(name);
        if(field != null){
            Result result = new Result(true,"根据id查询成功");
            result.setData(field);
            return result;
        }
        return new Result(false,"根据id查询失败");
    }
}
