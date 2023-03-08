package com.example.btl_web.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Blog implements Serializable {
    private Long blogId;
    private String title;
    private String content;
    private String imageTitle;
    private Date createAt;
    private User userBlog;
    Set<Category> categories = new HashSet<>();
    Set<User> likedUsers = new HashSet<>();
    Set<Comment> comments = new HashSet<>();
    public Blog(){

    }

    public Blog(Long blogId, String title, String content, String imageTitle, Date createAt, User userBlog) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.imageTitle = imageTitle;
        this.createAt = createAt;
        this.userBlog = userBlog;
    }
    public String timeConvert(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String data[] =  dateFormat.format(createAt).split("\\s+");
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public User getUserBlog() {
        return userBlog;
    }

    public void setUserBlog(User userBlog) {
        this.userBlog = userBlog;
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
}