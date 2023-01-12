package com.qipt.service;

import com.github.pagehelper.PageInfo;
import com.qipt.pojo.Blog;
import com.qipt.pojo.Type;

import java.util.List;
import java.util.Map;

public interface TypeService {
    Type insert(Type type);

    Type update(Long id, Type type);

    void delete(Long id);

    Type selectOne(String name);

    Type selectOne(Long id);

    List<Type> selectList();

    //查询所有type和一对多的blog
    Map<Type, Integer> selectList(int size);

    //查询所有type以及下面的blogs
     List<Type> selectBList();

    //分页查询
    PageInfo<Type> selectList(int page, int size);

}
