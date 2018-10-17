package com.lm.springthread.service;

import com.lm.springthread.entity.Type;

public interface TypeService {

    void selectType(Type type)throws Exception;

    void deleteType(Type type)throws Exception;

    void updateType(Type type)throws Exception;

    boolean insertType(Type type)throws Exception;
}
