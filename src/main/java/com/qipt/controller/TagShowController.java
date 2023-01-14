package com.qipt.controller;

import com.qipt.pojo.Tag;
import com.qipt.response.ResponseData;
import com.qipt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagShowController {

    @Autowired
    private TagService tagService;

    /**
     * 查询所有tag，和该tag下的所有blog
     */
    @GetMapping("")
    public ResponseData typesBlogs() {
        ResponseData responseData = null;
        //查询所有type
        List<Tag> list = tagService.selectList();
        if (list != null) {
            responseData = new ResponseData(1, "查询成功", list);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }
}
