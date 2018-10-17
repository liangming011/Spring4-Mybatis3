package com.lm.springaop.service;

import com.lm.springaop.entity.Type;

public interface TypeService {

    Type selectType(Type type) throws Exception;

    boolean deleteType(Type type) throws Exception;

    boolean updateType(Type type) throws Exception;

    boolean insertType(Type type) throws Exception;
}
