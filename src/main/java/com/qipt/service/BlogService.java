package com.qipt.service;

import com.github.pagehelper.PageInfo;
import com.qipt.pojo.Blog;

public interface BlogService {
    Blog insert(Blog blog);

    Blog update(Long id, Blog blog);

    void delete(Long id);

    Blog selectOne(Long id);

    Blog selectOne(String title);

    //分页查询
    PageInfo<Blog> selectList(int page, int size, String title, Long tagId);

}
