package com.lm.springthread.dao.impl;

import com.lm.springthread.dao.TypeDao;
import com.lm.springthread.entity.Type;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TypeDaoImpl extends SqlSessionDaoSupport implements TypeDao {

    @Override
    public Type selectType(Type type) throws Exception {
        return null;
    }

    @Override
    public boolean deleteType(Type type) throws Exception {
        return false;
    }

    @Override
    public boolean updateType(Type type) throws Exception {
        return false;
    }

    @Override
    public boolean insertType(Type type) throws Exception {
        return false;
    }
}
