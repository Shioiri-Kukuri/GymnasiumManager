package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.PageResult;
import com.vky.entity.QueryPageBean;
import com.vky.entity.Result;
import com.vky.pojo.Type;
import com.vky.service.TypeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Reference
    private TypeService typeService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    public Result findAll(){
        List<Type> typeList = typeService.findAll();
        if (typeList != null && typeList.size() > 0) {
            Result result = new Result(true, "查询成功", typeList);
            result.setData(typeList);
            return result;
        }
        return new Result(false, "查询失败");
    }

    @RequestMapping("/findById.do")
    public Result findById(Integer id){
        Type type = typeService.getById(id);
        if(type != null){
            Result result = new Result(true, "查询成功");
            result.setData(type);
            return result;
        }
        return new Result(false,"查询失败");
    }


    /**
     * 新增
     * @param type
     * @return
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody Type type){
        try {
            typeService.save(type);
        }catch (Exception e){
            //新增失败
            return new Result(false, "新增失败");
        }
        //新增成功
        return new Result(true,"新增成功");
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = typeService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public Result delete(Integer id){
        try {
            typeService.deleteById(id);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    /**
     * 编辑
     * @param type
     * @return
     */
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody Type type){
        try {
            typeService.edit(type);
        }catch (Exception e){
            return new Result(false,"编辑失败");
        }
        return new Result(true,"编辑成功");
    }

}
