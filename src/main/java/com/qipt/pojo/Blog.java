package com.qipt.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Blog implements Serializable {
    private Long id;
    @NotBlank(message = "标题不能为空")
    private String title;  //标题
    private String content;  //内容
    private String firstPicture;  //首图
    private String flag; //原创 转载 ...
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private String description;  //描述

    private List<Tag> tags; //对多

    private User user; //对一

    private List<Comment> comments; //对多

    public Blog() {
    }

    public List<Tag> getTags() {
        return tags;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateTime(Date createTime) {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
        Timestamp goodsC_date = Timestamp.valueOf(nowTime);//把时间转换
        this.createTime = goodsC_date;
    }

    public void setUpdateTime(Date updateTime) {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
        Timestamp goodsC_date = Timestamp.valueOf(nowTime);//把时间转换
        this.updateTime = goodsC_date;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ",title:" + title +
                ",content:" + content +
                ",firstPicture:" + firstPicture +
                ",flag:" + flag +
                ",createTime:" + createTime +
                ",updateTime:" + updateTime +
                ",description:" + description +
                ",tags:" + tags +
                ",user:"+ user +
                ",comments:" + comments +
                '}';
    }
}
