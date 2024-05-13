package com.vky.dao;


import com.github.pagehelper.Page;
import com.vky.pojo.Competition;

public interface CompetitionDao{
    void save(Competition competition);

    Page<Competition> selectByCondition(String queryString);

    Competition getById(Integer id);

    void deleteById(Integer id);

    void edit(Competition competition);
}
