package com.qipt.service.impl;

import com.qipt.mapper.CommentMapper;
import com.qipt.pojo.Comment;
import com.qipt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    private List<Comment> tempList = new ArrayList<>();

    /**
     * 查询给定id的blog下所有comment
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> selectList(Long blogId) {
        return commentMapper.selectListByBlog(blogId);
    }

    @Override
    public Comment insert(Comment comment) {
        try {
            comment.setCreateTime(new Date());
            Long parentCommentId = comment.getParentComment().getId();
            if (parentCommentId == -1) {
                comment.setParentComment(null);
            } else {
                //查出父评论 封装
                Comment parentComment = commentMapper.selectOne(parentCommentId);
                comment.setParentComment(parentComment);
            }
            commentMapper.insert(comment);
            return comment;
        } catch (Exception e) {
            return null;
        }

    }


}
