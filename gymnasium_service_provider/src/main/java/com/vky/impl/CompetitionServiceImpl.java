package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.CompetitionDao;
import com.vky.entity.PageResult;
import com.vky.pojo.Competition;
import com.vky.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CompetitionService.class)
@Transactional
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public void save(Competition competition) {
        competitionDao.save(competition);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Competition> page = competitionDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Competition getById(Integer id) {
        return competitionDao.getById(id);
    }

    @Override
    public void deleteById(Integer id) throws RuntimeException{
        competitionDao.deleteById(id);
    }

    @Override
    public void edit(Competition competition) {
        competitionDao.edit(competition);
    }
}

