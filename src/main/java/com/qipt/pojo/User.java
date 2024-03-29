package com.qipt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//用户
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String avatar; //头像
    private Date createTime;
    private Date updateTime;

    private List<Blog> blogs; //对多

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ",username:" + username +
                ",password:" + password +
                ",email:" + email +
                ",avatar:" + avatar +
                ",createTime:" + createTime +
                ",updateTime:" + updateTime +
                '}';
    }
}
