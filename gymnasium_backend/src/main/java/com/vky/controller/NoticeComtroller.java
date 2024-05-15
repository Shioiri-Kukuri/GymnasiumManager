package com.vky.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.entity.PageResult;
import com.vky.entity.QueryPageBean;
import com.vky.entity.Result;
import com.vky.pojo.Notice;
import com.vky.service.NoticeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeComtroller {

    @Reference
    private NoticeService noticeService;

    /**
     * 查询所有公告
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public Result findAll() {
        List<Notice> noticeList = noticeService.findAll();
        if (noticeList != null && noticeList.size() > 0) {
            Result result = new Result(true, "查询成功");
            result.setData(noticeList);
            return result;
        }
        return new Result(false, "查询失败");
    }

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public Result findById(Integer id) {
        Notice notice = noticeService.getById(id);
        if (notice != null) {
            Result result = new Result(true, "查询成功");
            result.setData(notice);
            return result;
        }
        return new Result(false, "查询失败");
    }

    /**
     * 添加公告
     *
     * @param notice
     * @return
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody Notice notice) {
        try {
            noticeService.save(notice);
        } catch (Exception e) {
            //新增失败
            return new Result(false, "新增失败");
        }
        //新增成功
        return new Result(true, "新增成功");
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = noticeService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public Result delete(Integer id) {
        try {
            noticeService.deleteById(id);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, "删除失败");
        }
        return new Result(true, "删除成功");
    }

    /**
     * 编辑
     *
     * @param notice
     * @return
     */
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody Notice notice) {
        try {
            noticeService.edit(notice);
        } catch (Exception e) {
            return new Result(false, "编辑失败");
        }
        return new Result(true, "编辑成功");
    }
}