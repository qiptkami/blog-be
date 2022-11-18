package com.qipt.controller;

import com.github.pagehelper.PageInfo;
import com.qipt.pojo.Blog;
import com.qipt.response.ResponseData;
import com.qipt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/archives")
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/pagination")
    public ResponseData archives(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) {
        ResponseData responseData = null;
        PageInfo<Blog> pageInfo = blogService.selectList(page, size);
        if (pageInfo != null) {
            responseData = new ResponseData(1, "查询成功", pageInfo);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }
}
