package com.lm.springtest.service.impl;

import com.lm.springtest.dao.TypeDao;
import com.lm.springtest.entity.Type;
import com.lm.springtest.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public Type selectType(Type type) throws Exception {
        return typeDao.selectType(type);
    }

    @Override
    public boolean deleteType(Type type) throws Exception {
        return typeDao.deleteType(type);
    }

    @Override
    public boolean updateType(Type type) throws Exception {
        return typeDao.updateType(type);
    }

    @Override
    public boolean insertType(Type type) throws Exception {
        return typeDao.insertType(type);
    }

}
