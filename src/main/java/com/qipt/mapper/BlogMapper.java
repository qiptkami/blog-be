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

    //首页查询
    List<Blog> selectListQuery(String query);

    //后端条件查询
    List<Blog> selectListMultipleConditional(String title, Long typeId);

    void insert(Blog blog);

    void update(Blog blog);

    void delete(Long id);
}
