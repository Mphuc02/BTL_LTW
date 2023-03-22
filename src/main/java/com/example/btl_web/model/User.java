package com.example.btl_web.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private Long userId;
    private String userName;
    private String passWord;
    private String email;
    private String role;
    private String address;
    private String phone;
    private String fullName;
    private Date registeredAt;
    private Set<Blog> blogs = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();
    private Set<Blog> likedBlog = new HashSet<>();
    public User(){

    }
    public void removeLikedBlog(Blog blog) {
        this.likedBlog.remove(blog);
        blog.getLikedUsers().remove(this);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Blog> getLikedBlog() {
        return likedBlog;
    }

    public void setLikedBlog(Set<Blog> likedBlog) {
        this.likedBlog = likedBlog;
    }
}
