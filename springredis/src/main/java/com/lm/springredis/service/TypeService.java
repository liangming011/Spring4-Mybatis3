package com.lm.springredis.service;

import com.lm.springredis.entity.Type;

public interface TypeService {

    Type selectType(Type type) throws Exception;

    boolean deleteType(Type type) throws Exception;

    boolean updateType(Type type) throws Exception;

    boolean insertType(Type type) throws Exception;
}
