package com.example.btl_web.constant;

public class Constant {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String USER_MODEL = "USER_MODEL";
    public static final String LOGIN_JSP = "/views/login/login.jsp";
    public static final String REGISTRAION_JSP = "/views/registraion/registraion.jsp";
    public static final String USER_NAME = "username";
    public static final String PASS_WORD = "password";
    public static class User
    {
        public static final String ACTION = "action";
        public static final String ACTION_NOT_LOGIN = "not_login";
        public static final String ACTION_NOT_PERMISSION = "not_permission";
        public static final String ACTION_LOG_OUT = "logout";
        public static final String LOGIN_PAGE = "/login";
        public static final String HOME_PAGE = "/";
        public static final String HOME_JSP = "/views/home/home.jsp";
        public static final String SEARCH_JSP = "/views/home/search.jsp";
        public static final String CREATE_BLOG_PAGE = "/create-blog";
        public static final String CREATE_BLOG_JSP = "/views/blog/create_blog.jsp";
    }
    public static class Admin
    {
        public static final String EDIT = "/edit";
        public static final String CATEGORIES_PAGE = "/admin/categories";
        public static final String CATEGORY_API = "/api-admin-category";
        public static final String CATEGORIES_JSP = "/views/admin/categories.jsp";
        public static final String CATEGORY_EDIT_JSP = "/views/admin/create/categories.jsp";
        public static final String BLOGS_PAGE = "/admin/blogs";
        public static final String BLOGS_API = "/api-admin-blog";
        public static final String BLOGS_JSP = "/views/admin/blogs.jsp";
        public static final String USERS_PAGE = "/admin/users";
        public static final String USER_API = "/api-admin-user";
        public static final String USERS_JSP = "/views/admin/users.jsp";
        public static final String STATISTIC_PAGE = "/admin/statistic";
        public static final String STATISTIC_JSP = "/views/admin/statistic.jsp";
    }

    public static class Dto
    {
        public static final String CREATE_DATE = "createdAt";
        public static final String REGISTRATION_AT = "registeredAt";
        public static final String MODIFIED_DATE = "modifiedAt";
        public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    }

    public static class Paging
    {
        public static final int MAX_ITEMS_OF_PAGE = 5;
        public static final String PAGE_STR = "page";
        public static final String MAX_ITEMS_OF_PAGE_STR = "max-items-page";
        public static final String SORT_NAME = "sort-name";
        public static final String SORT_BY = "sort-by";
    }
}
