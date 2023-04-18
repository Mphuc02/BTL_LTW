package com.example.btl_web.model;

import com.example.btl_web.dto.UserDto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class Blog implements Serializable {
    private Long blogId;
    private String title;
    private String content;
    private String imageTitle;
    private Long createdAt;
    private UserDto user;
    private Integer status;
    Set<Category> categories = new HashSet<>();
    Set<User> likedUsers = new HashSet<>();
    Set<Comment> comments = new HashSet<>();

    public Blog(){

    }
    public String timeConvert(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String data[] =  dateFormat.format(createdAt).split("\\s+");
        String date = data[0] + " lúc " + data[1];
        return date;
    }
    public void removeLikedUser(User user)
    {
        this.likedUsers.remove(user);
        user.getLikedBlog().remove(this);
    }
    public void addLikedUser(User user){
        this.likedUsers.add(user);
        user.getLikedBlog().add(this);
    }
    public Boolean checkLike(String name){
        for(User user: likedUsers){
            if(user.getUserName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createAt) {
        this.createdAt = createAt;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(Set<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}