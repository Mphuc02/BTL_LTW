package com.example.btl_web.paging;

import java.util.Map;

import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.service.impl.CategoryServiceImpl;

public class PageRequest implements Pageable{
    private Integer totalPages;
    private Integer page;
    private Integer offset;
    private Integer limit;
    private String sortName;
    private String sortBy;
    public PageRequest(Map<String, String[]> paramters)
    {
        String pageStr[] = paramters.get(Paging.PAGE_STR);
        if(pageStr == null)
            page = 1;
        else
            page = Integer.parseInt(pageStr[0]);

        String maxItemsPageStr[] = paramters.get(Paging.MAX_ITEMS_OF_PAGE_STR);
        if(maxItemsPageStr == null)
            limit = Paging.MAX_ITEMS_OF_PAGE;
        else
            limit = Integer.parseInt(maxItemsPageStr[0]);

        String sortNameStr[] = paramters.get(Paging.SORT_NAME);
        String sortByStr[] = paramters.get(Paging.SORT_BY);

        if(sortNameStr != null)
            sortName = sortNameStr[0];
        if(sortByStr != null)
        {
            sortBy = sortByStr[0];
            if(!sortBy.equals("asc") || !sortBy.equals("desc"))
                sortBy = "asc";
        }

        offset = 0;
        if(this.page != null && this.limit != null)
            offset = (this.page - 1) * this.limit;

        CategoryService categoryService = CategoryServiceImpl.getInstance();
        long totalItems = categoryService.countAllCategory();
        this.totalPages = (int) Math.ceil(1.0 * totalItems / limit);
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getOffset() {
        return offset;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public Integer getTotalPages() {
        return totalPages;
    }

    @Override
    public String getSortName() {
        return sortName;
    }

    @Override
    public String getSortBy() {
        return sortBy;
    }

}
