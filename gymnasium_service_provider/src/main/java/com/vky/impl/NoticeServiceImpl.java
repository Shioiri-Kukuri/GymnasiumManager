package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.NoticeDao;
import com.vky.entity.PageResult;
import com.vky.pojo.Notice;
import com.vky.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = NoticeService.class)
@Transactional
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<Notice> findAll() {
        return noticeDao.findAll();
    }

    @Override
    public Notice getById(Integer id) {
        return noticeDao.getById(id);
    }

    @Override
    public void save(Notice notice) {
        noticeDao.save(notice);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Notice> page = noticeDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        noticeDao.deleteById(id);
    }

    @Override
    public void edit(Notice notice) {
        noticeDao.edit(notice);
    }
}
