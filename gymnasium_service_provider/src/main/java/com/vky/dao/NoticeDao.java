package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.annotation.AutoFill;
import com.vky.enumeration.OperationType;
import com.vky.pojo.Notice;

import java.util.List;

public interface NoticeDao {
    List<Notice> findAll();

    Notice getById(Integer id);

    @AutoFill(value = OperationType.INSERT)
    void save(Notice notice);

    Page<Notice> selectByCondition(String queryString);

    void deleteById(Integer id);

    @AutoFill(value = OperationType.UPDATE)
    void edit(Notice notice);
}
