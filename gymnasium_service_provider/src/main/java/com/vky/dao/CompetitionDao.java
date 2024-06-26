package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.annotation.AutoFill;
import com.vky.enumeration.OperationType;
import com.vky.pojo.Competition;

public interface CompetitionDao{
    @AutoFill(value = OperationType.INSERT)
    void save(Competition competition);

    Page<Competition> selectByCondition(String queryString);

    Competition getById(Integer id);

    void deleteById(Integer id);

    @AutoFill(value = OperationType.UPDATE)
    void edit(Competition competition);
}
