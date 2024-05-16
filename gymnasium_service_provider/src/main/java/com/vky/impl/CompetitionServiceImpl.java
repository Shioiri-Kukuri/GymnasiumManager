package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.CompetitionDao;
import com.vky.dao.FieldDao;
import com.vky.entity.PageResult;
import com.vky.pojo.Competition;
import com.vky.pojo.Field;
import com.vky.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CompetitionService.class)
@Transactional
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    private CompetitionDao competitionDao;

    @Autowired
    private FieldDao fieldDao;

    @Override
    public void save(Competition competition) {
        competitionDao.save(competition);
        try {
            String fieldName = competition.getFieldName();
            Field field = fieldDao.findByName(fieldName);
            field.setFieldStatus(0);
        }catch (Exception e){
            System.out.println("场地不存在");
        }
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

