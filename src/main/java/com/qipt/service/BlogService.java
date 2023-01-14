package com.qipt.service;

import com.github.pagehelper.PageInfo;
import com.qipt.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog insert(Blog blog);

    Blog update(Long id, Blog blog);

    void delete(Long id);

    Blog selectOne(Long id);

    Blog selectOne(String title);

    //分页查询
    PageInfo<Blog> selectList(int page, int size);

    //首页按照输入框的内容条件查询
    PageInfo<Blog> selectList(int page, int size, String query);

    //查询所有符合联合条件的
    PageInfo<Blog> selectList(int page, int size, String title, Long typeId);

}
