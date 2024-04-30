package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vky.dao.CompetitionDao;
import com.vky.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompetitionServiceImpl {

    @Reference
    private CompetitionDao competitionDao;
}
