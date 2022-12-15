package com.qipt.mapper;

import com.qipt.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    //查询给定id的blog下所有comment
    List<Comment> selectListByBlog(Long blogId);

    //通过id查询
    Comment selectOne(Long id);

    void insert(Comment comment);

}
