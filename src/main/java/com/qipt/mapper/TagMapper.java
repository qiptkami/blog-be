package com.qipt.mapper;

import com.qipt.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    void insert(Tag type);

    void update(Tag type);

    void delete(Long id);

    Tag selectOneById(Long id);

    Tag selectOneByName(String name);

    List<Tag> selectList();

}
