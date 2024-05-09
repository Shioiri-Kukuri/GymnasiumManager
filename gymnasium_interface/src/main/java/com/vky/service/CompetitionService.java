package com.vky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vky.entity.PageResult;
import com.vky.pojo.Competition;

public interface CompetitionService {
    void save(Competition competition);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    Competition getById(Integer id);

    void deleteById(Integer id);

    void edit(Competition competition);
}
