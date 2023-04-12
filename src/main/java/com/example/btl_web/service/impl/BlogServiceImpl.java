package com.example.btl_web.service.impl;

import com.example.btl_web.dao.BlogDao;
import com.example.btl_web.dao.impl.BlogDaoImpl;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.model.Blog;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogServiceImpl implements BlogService {
    private BlogDao blogDao = BlogDaoImpl.getInstance();
    private static BlogServiceImpl blogService;
    public static BlogServiceImpl getInstance()
    {
        if(blogService == null)
            blogService = new BlogServiceImpl();
        return blogService;
    }

    @Override
    public List<BlogDto> getAllBlogs(Pageable pageable, BlogDto dto) {
        StringBuilder sql = new StringBuilder("SELECT * FROM BLOGS WHERE (1 = 1)");

        sql.append(addAndClause(pageable, dto));

        List<Blog> blogs = blogDao.findAll(sql.toString());
        List<BlogDto> dtos = new ArrayList<>();

        for(Blog blog: blogs)
        {
            dtos.add(ConvertUtils.convertEntityToDto(blog, BlogDto.class));
        }

        return dtos;
    }

    @Override
    public long countBlogs(BlogDto blogDto) {
        StringBuilder sql = new StringBuilder("SELECT count(blog_id) FROM BLOGS WHERE ( 1 = 1)");
        if(blogDto != null)
            sql.append(addAndClause(null, blogDto));
        return blogDao.countBlogs(sql.toString());
    }

    @Override
    public Boolean save(Blog blog) {
        Date timeStamp = new Date();
        blog.setCreateAt(timeStamp.getTime());
        String sql = "INSERT INTO BLOGS (content, created_at, image_title, title, user_id)";
        return blogDao.save(sql,blog.getContent(), blog.getCreateAt(), blog.getImageTitle(), blog.getUserBlog().getUserId());
    }

    private StringBuilder addAndClause(Pageable pageable ,BlogDto dto)
    {
        StringBuilder sb = new StringBuilder();

        if(dto != null) {
            Long blogId = dto.getBlogId();
            String title = dto.getTitle();
            String content = dto.getContent();
            String imageTitle = dto.getImageTitle();
            String createAt = dto.getCreateAt();

            if (blogId != null)
                sb.append(" AND WHERE blog_id = " + blogId);
            if (title != null)
                sb.append(" AND title like '%" + title + "%'");
            if (content != null)
                sb.append(" AND content like '%" + content + "%'");
            if (imageTitle != null)
                sb.append(" AND image_title = " + imageTitle);
            if (createAt != null)
                sb.append(" AND created_at = " + createAt);
        }

        if(pageable != null)
            sb.append(pageable.addPagingation());

        return sb;
    }
}
