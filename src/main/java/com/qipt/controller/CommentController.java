package com.qipt.controller;

import com.qipt.pojo.Comment;
import com.qipt.pojo.User;
import com.qipt.response.ResponseData;
import com.qipt.service.BlogService;
import com.qipt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/{blogId}")
    public ResponseData commentList(@PathVariable Long blogId) {
        ResponseData responseData = null;
        List<Comment> list = commentService.selectList(blogId); //根据blogId查询出所有没有parentComment的Comment
        if (list != null) {
            responseData = new ResponseData(1, "查询成功", list);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

    @PostMapping("")
    public ResponseData insert(@RequestBody Comment comment, HttpServletRequest request) {
        System.out.println("123"+comment);
        ResponseData responseData = null;
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.selectOne(blogId));

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) { //管理员
            String avatar = user.getAvatar();
            String username = user.getUsername();
            comment.setAvatar(avatar);
            comment.setNickname(username);
            comment.setAdminComment(true);
        } else { //游客
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        Comment m = commentService.insert(comment);
        if (m != null) {
            responseData = new ResponseData(1, "插入成功", m);
        } else {
            responseData = new ResponseData(0, "插入失败", null);
        }
        return responseData;
    }
}
