package com.qipt.controller;

import com.github.pagehelper.PageInfo;
import com.qipt.pojo.Blog;
import com.qipt.response.ResponseData;
import com.qipt.service.BlogService;
import com.qipt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class BlogShowController {

    @Autowired
    BlogService blogService;

    @Autowired
    TagService typeService;

    @GetMapping("/pagination")
    public ResponseData pagination(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                   @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) {
        ResponseData responseData = null;
        PageInfo<Blog> pageInfo = blogService.selectList(page, size);
        System.out.println(pageInfo.getList().size());
        if (pageInfo != null) {
            responseData = new ResponseData(1, "查询成功", pageInfo);
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

    @GetMapping("/{id}")
    public ResponseData blog(@PathVariable Long id) {
        ResponseData responseData = null;
        Blog blog = blogService.selectOne(id);
//        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        if (blog != null) {
            responseData = new ResponseData(1, "查询成功", blog);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

}
