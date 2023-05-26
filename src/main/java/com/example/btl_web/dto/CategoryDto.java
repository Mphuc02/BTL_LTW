package com.example.btl_web.dto;

public class CategoryDto {
    private Long categoryId;
    private String name;
    private UserDto user;
    private Long userId;
    private Long blogsHaveCategory;
    private String createdAt;
    private Integer status;
    public CategoryDto()
    {}
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getUserId() {
        return userId;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBlogsHaveCategory() {
        return blogsHaveCategory;
    }

    public void setBlogsHaveCategory(Long blogsHaveCategory) {
        this.blogsHaveCategory = blogsHaveCategory;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
