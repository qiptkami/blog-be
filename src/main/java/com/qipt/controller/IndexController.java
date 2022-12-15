package com.qipt.controller;

import com.github.pagehelper.PageInfo;
import com.qipt.pojo.Blog;
import com.qipt.pojo.Type;
import com.qipt.response.ResponseData;
import com.qipt.service.BlogService;
import com.qipt.service.TypeService;
import com.qipt.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired TypeService typeService;

    @GetMapping("/pagination")
    public ResponseData pagination(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                 @RequestParam(name = "size", required = true, defaultValue = "6") Integer size) {
        ResponseData responseData = null;
        PageInfo<Blog> pageInfo = blogService.selectList(page, size, true);
        if (pageInfo != null) {
            responseData = new ResponseData(1, "查询成功", pageInfo);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

    @GetMapping("/recommend" )
    public ResponseData recommend() {
        ResponseData responseData = null;
        List<Blog> recommendBlog = blogService.selectList(6);
        if (recommendBlog != null) {
            responseData = new ResponseData(1, "查询成功", recommendBlog);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

    @GetMapping("/ranking")
    public ResponseData ranking() {
        ResponseData responseData = null;
        Map<Type, Integer> rankingMap = typeService.selectList(6);

        if (rankingMap != null) {
            responseData = new ResponseData(1, "查询成功", rankingMap);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }

        return responseData;
    }

    @GetMapping("/search")
    public ResponseData search(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                         @RequestParam(name = "size", required = true, defaultValue = "6") Integer size,
                         String query) {
        ResponseData responseData = null;
        PageInfo<Blog> pageInfo = blogService.selectList(page, size, query);
        if (pageInfo != null) {
            responseData = new ResponseData(1, "查询成功", pageInfo);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

    @GetMapping("/blog/{id}")
    public ResponseData blog(@PathVariable Long id) {
        ResponseData responseData = null;
        Blog blog = blogService.selectOne(id);
        blogService.incrView(id, blog);
//        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        if (blog != null) {
            responseData = new ResponseData(1, "查询成功", blog);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

}
