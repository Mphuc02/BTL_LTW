package com.example.btl_web.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Serializable {
    private Long commentId;
    private String content;
    private User userComment;
    private Blog blogComment;
    private Date createdAt;
    public Comment() {

    }

    public Comment(Long commentId, String content, User userComment, Blog blogComment, Date createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.userComment = userComment;
        this.blogComment = blogComment;
        this.createdAt = createdAt;
    }

    public String timeConvert(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String data[] = dateFormat.format(createdAt).split("\\s+");
        String date = data[0] + " lúc " + data[1];
        return date;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(User userComment) {
        this.userComment = userComment;
    }

    public Blog getBlogComment() {
        return blogComment;
    }

    public void setBlogComment(Blog blogComment) {
        this.blogComment = blogComment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
