package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.TypeDao;
import com.vky.entity.PageResult;
import com.vky.pojo.Type;
import com.vky.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = TypeService.class)
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;
    @Override
    public void save(Type type) {
        typeDao.add(type);
    }

    @Override
    public List<Type> findAll() {
        return typeDao.findAll();
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Type> page = typeDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) throws RuntimeException {
        typeDao.deleteById(id);
    }

    @Override
    public void edit(Type type) {
        typeDao.edit(type);
    }

    @Override
    public Type getById(Integer id) {
        return typeDao.getById(id);
    }
}


