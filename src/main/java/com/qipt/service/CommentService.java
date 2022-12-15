package com.qipt.service;

import com.qipt.pojo.Comment;

import java.util.List;

public interface CommentService {

    Comment insert(Comment comment);

    List<Comment> selectList(Long blogId);

}
