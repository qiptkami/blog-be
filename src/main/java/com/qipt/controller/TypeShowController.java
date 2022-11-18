package com.qipt.controller;

import com.github.pagehelper.PageInfo;
import com.qipt.pojo.Blog;
import com.qipt.pojo.Type;
import com.qipt.response.ResponseData;
import com.qipt.service.BlogService;
import com.qipt.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/types")
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 查询所有type，按照type下的blog数量排序
     */
    @GetMapping("/")
    public ResponseData types() {
        ResponseData responseData = null;
        //查询所有type
        Map<Type, Integer> map = typeService.selectList(10000);
        if (map != null) {
            responseData = new ResponseData(1, "查询成功", map);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }

    /**
     * 按照分类查询blog，默认查询数量最多的分类喜爱的blog
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GetMapping({"/pagination", "pagination/{id}"})
    public ResponseData pagination(@PathVariable(required = false) Long id,
                                   @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                   @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) {
        ResponseData responseData = null;
        if (id == null) {
            id = -1L;
        }
        //首先查询所有type
        Map<Type, Integer> map = typeService.selectList(10000);
        if (id == -1) {  //从导航栏点击过去 默认-1
            Set<Type> types = map.keySet();
            for (Type type : types) {
                if (type != null) {
                    id = type.getId();  //默认取blog最多的type
                    break;
                }
            }
        }
        //查询给定id的type下所有的blog 对blog分页
        PageInfo<Blog> pageInfo = blogService.selectList(page, size, null, id, true);
        if (pageInfo != null) {
            responseData = new ResponseData(1, "查询成功", pageInfo);
        } else {
            responseData = new ResponseData(0, "未查询到结果", null);
        }
        return responseData;
    }
}
