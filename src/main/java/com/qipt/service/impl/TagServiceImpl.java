package com.qipt.service.impl;

import com.qipt.mapper.TagMapper;
import com.qipt.pojo.Tag;
import com.qipt.service.TagService;
import com.qipt.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Tag selectOne(Long id) {
        String key = "tags";
        //首先查缓存
        Tag tag = (Tag) redisUtils.hGet(key, String.valueOf(id));
        if (tag == null) {
            tag = tagMapper.selectOneById(id);
            redisUtils.hSet(key, String.valueOf(id), tag);
        }
        return tag;
    }

    @Override
    public Tag selectOne(String name) {
        return tagMapper.selectOneByName(name);
    }

    /**
     * 查询出所有的tag和blog
     * @return
     */
    @Override
    public List<Tag> selectList() {
        String key = "tags";
        List<Tag> tags = new ArrayList<>();
        boolean exists = redisUtils.exists(key);
        if (!exists) {
            tags = tagMapper.selectList();
            for (Tag tag : tags) {
                redisUtils.hSet(key, String.valueOf(tag.getId()), tag);
            }
        } else {
            Map<Object, Object> map = redisUtils.hGetAll(key);
            Set<Map.Entry<Object, Object>> entries = map.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                tags.add((Tag) entry.getValue());
            }
        }
        return tags;
    }

    @Override
    public Tag insert(Tag tag) {
        //首先判断数据库中有无这个数据
        Tag t = tagMapper.selectOneByName(tag.getName());
        if (t != null) {
            return null;
        }
        tagMapper.insert(tag);
        //放入缓存
        redisUtils.hSet("tags", String.valueOf(tag.getId()), tag);
        return tag;
    }

    @Override
    public Tag update(Long id, Tag type) {
        Tag t = tagMapper.selectOneById(id);
        if (t == null) {
            return null;
        }
        tagMapper.update(type);
        //删除缓存
        redisUtils.hDel("tags", String.valueOf(type.getId()));

        return type;
    }

    @Override
    public void delete(Long id) {
        boolean flag = true;  //是否可以删除
        //首先需要先判断是否有博客是这个tag
        List<Tag> list = this.selectList();
        for (Tag t : list) {
            if (t.getId().equals(id)) {
                if (t.getBlogs().size() > 0) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            //如果没有，才能删除
            tagMapper.delete(id);
            redisUtils.hDel("tags", String.valueOf(id));
        } else {
            throw new RuntimeException("该类型下还有所属博客！！！");
        }

    }

}
