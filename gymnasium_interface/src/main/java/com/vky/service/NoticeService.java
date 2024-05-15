package com.vky.service;

import com.vky.entity.PageResult;
import com.vky.pojo.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> findAll();

    Notice getById(Integer id);

    void save(Notice notice);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(Integer id);

    void edit(Notice notice);
}
