package com.qipt.mapper;

import com.qipt.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {

    Blog selectOneById(Long id);

    Blog selectOneByTitle(String title);

    //查询所有blog
    List<Blog> selectList();

    List<Blog> selectListConditional(String title, Long tagId);

    void insert(Blog blog);

    void update(Blog blog);

    void delete(Long id);
}
