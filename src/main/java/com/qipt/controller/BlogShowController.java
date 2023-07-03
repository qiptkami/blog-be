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
    public ResponseData pagination(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                   @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                   @RequestParam(name = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(name = "tagId", required = false, defaultValue = "0") Long tagId
    ) {
        ResponseData responseData = null;
        PageInfo<Blog> pageInfo = blogService.selectList(page, size, title, tagId);
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
//        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog .getContent()));
        if (blog != null) {
            responseData = new ResponseData(1, "查询成功", blog);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

}
