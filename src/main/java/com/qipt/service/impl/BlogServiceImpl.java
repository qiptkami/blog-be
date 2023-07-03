package com.qipt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qipt.mapper.BlogMapper;
import com.qipt.pojo.Blog;
import com.qipt.service.BlogService;
import com.qipt.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog selectOne(Long id) {
        //首先查缓存
        String key = "blogs";
        boolean exists = redisUtils.exists(key);
        if (!exists) {
            Blog blog = blogMapper.selectOneById(id);
            redisUtils.hSet(key, String.valueOf(id), blog);
        }
        return (Blog) redisUtils.hGet(key, String.valueOf(id));
    }

    @Override
    public Blog selectOne(String title) {
        return blogMapper.selectOneByTitle(title);
    }

    @Override
    public PageInfo<Blog> selectList(int page, int size, String title, Long tagId) {
        if (Objects.equals(title, "") && tagId.toString().equals("0")) {
            String key = "blogs";
            List<Blog> blogs;
            boolean exists = redisUtils.exists(key);
            if (!exists) {
                blogs = blogMapper.selectList();
                for (Blog blog : blogs) {
                    redisUtils.setPage(key, String.valueOf(blog.getId()), blog.getId().doubleValue(), blog);
                }
            } else {
                List<Object> list = redisUtils.getPage(key, page, size);
                blogs = new ArrayList<>();
                for (Object blog : list) {
                    blogs.add((Blog) blog);
                }
            }
            return getPageInfo(page, size, blogs, key);
        } else {
            PageHelper.startPage(page, size);
            List<Blog> list = blogMapper.selectListConditional(title, tagId);
            return new PageInfo<>(list);
        }
    }

    @Override
    public Blog update(Long id, Blog blog) {
        String key = "blogs";
        Blog b = blogMapper.selectOneById(id);
        if (b == null) {
            return null;
        }
        blog.setUpdateTime(new Date());
        blogMapper.update(blog);
        blog = blogMapper.selectOneById(id);

        //删除缓存
        redisUtils.hDel(key, String.valueOf(blog.getId()));

        return blog;
    }

    @Override
    public Blog insert(Blog blog) {
        Blog b = blogMapper.selectOneByTitle(blog.getTitle());
        if (b != null) {
            return null;
        }
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blogMapper.insert(blog);

        //分页缓存
        redisUtils.setPage("blogs", String.valueOf(blog.getId()), blog.getId().doubleValue(), blog);

        String key = "type_blogs";
        boolean exist = redisUtils.exists(key);
        return blog;
    }

    @Override
    public void delete(Long id) {
        Blog blog = (Blog) redisUtils.hGet("blogs", String.valueOf(id));
        blogMapper.delete(id);
        //删除分页缓存 blog 中的blog
        redisUtils.delPage("blog", String.valueOf(id));
    }

    private PageInfo<Blog> getPageInfo(int page, int size, List<Blog> list, String key) {
        Page<Blog> blogPage = new Page<>();
        blogPage.setPageNum(page);
        blogPage.setPageSize(size);
        blogPage.setTotal(redisUtils.getPageSize(key));
        PageInfo<Blog> pageInfo = new PageInfo<>(blogPage);
        pageInfo.setList(list);
        return pageInfo;
    }

}
