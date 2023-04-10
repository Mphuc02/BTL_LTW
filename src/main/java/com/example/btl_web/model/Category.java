package com.example.btl_web.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Category implements Serializable {
    private Long categoryId;
    private String name;
    private Set<Blog> blogs = new HashSet<>();
    public Category(){
        
    }

    public Category(Long categoryId, String name, Set<Blog> blogs) {
        this.categoryId = categoryId;
        this.name = name;
        this.blogs = blogs;
    }

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

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }
}
