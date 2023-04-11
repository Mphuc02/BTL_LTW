package com.example.btl_web.paging;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    String getSortName();
    String getSortBy();
}
