package com.qipt.service;

import com.qipt.pojo.Tag;

import java.util.List;

public interface TagService {
    Tag insert(Tag type);

    Tag update(Long id, Tag type);

    void delete(Long id);

    Tag selectOne(String name);

    Tag selectOne(Long id);

    List<Tag> selectList();

}
