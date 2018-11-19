package com.lm.springaop.service.impl;

import com.lm.springaop.dao.TypeDao;
import com.lm.springaop.entity.Type;
import com.lm.springaop.service.TypeService;
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
