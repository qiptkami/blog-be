package com.qipt.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

//分类
public class Tag implements Serializable {
    private Long id;
    @NotBlank(message = "标签名称不能为空")
    private String name;

    private List<Blog> blogs; //对多

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id+
                ",name:" + name +
                '}';
    }
}
