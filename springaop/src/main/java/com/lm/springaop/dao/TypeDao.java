package com.lm.springaop.dao;

import com.lm.springaop.entity.Type;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeDao {

    Type selectType(Type type) throws Exception;

    boolean deleteType(Type type) throws Exception;

    boolean updateType(Type type) throws Exception;

    boolean insertType(Type type) throws Exception;
}
