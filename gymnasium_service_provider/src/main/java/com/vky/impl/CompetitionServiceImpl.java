package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.vky.dao.CompetitionDao;
import com.vky.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CompetitionService.class)
@Transactional
public class CompetitionServiceImpl {

    @Autowired
    private CompetitionDao competitionDao;
}
