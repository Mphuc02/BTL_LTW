package com.example.btl_web.dto;

import com.example.btl_web.model.Comment;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.service.impl.CategoryServiceImpl;
import com.example.btl_web.utils.BytePartUtils;
import jakarta.servlet.http.Part;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlogDto {
    private Long blogId;
    private String title;
    private String content;
    private String imageTitle;
    private String createdAt;
    private UserDto user;
    private Part imageTitleData;
    private Integer status;
    Set<CategoryDto> categories = new HashSet<>();
    Set<UserDto> likedUsers = new HashSet<>();
    Set<Comment> comments = new HashSet<>();
    public BlogDto()
    {

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createAt) {
        this.createdAt = createAt;
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categoryIds) {
        this.categories = new HashSet<>();
        CategoryService categoryService = CategoryServiceImpl.getInstance();
        for(Long categoryId: categoryIds)
        {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(categoryId);
            this.categories.add(categoryService.findOneBy(categoryDto));
        }
    }

    public Set<UserDto> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(Set<UserDto> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    public Part getImageTitleData() {
        return imageTitleData;
    }

    public void setImageTitleData(String base64Image ) {
        byte[] imageData = Base64.getDecoder().decode(base64Image);

        this.imageTitleData = new BytePartUtils(imageData, "1");
    }

    public void setImageTitleData(Part imageTitleData) {
        this.imageTitleData = imageTitleData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
