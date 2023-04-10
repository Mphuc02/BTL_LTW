package com.example.btl_web.constant;

public class Constant {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String USER_MODEL = "USER_MODEL";
    public static final String LOGIN_JSP = "/views/login/login.jsp";
    public static final String REGISTRAION_JSP = "/views/registraion/registraion.jsp";
    public static class User
    {
        public static final String LOGIN = "/login";
        public static final String HOME_JSP = "/views/home/home.jsp";
    }
    public static class Admin
    {
        public static final String ADMIN_HOME = "/admin-home";
        public static final String CATEGORIES_PAGE = "categories";
        public static final String CATEGORIES_JSP = "/views/admin/categories.jsp";
        public static final String BLOGS_PAGE = "blogs";
        public static final String BLOGS_JSP = "/views/admin/blogs.jsp";
        public static final String USERS_PAGE = "users";
        public static final String USERS_JSP = "/views/admin/users.jsp";
        public static final String STATISTIC_PAGE = "statistic";
        public static final String STATISTIC_JSP = "/views/admin/statistic.jsp";
    }

    public static class Dto
    {
        public static final String CREATE_DATE = "created_at";
        public static final String REGISTRATION_AT = "registered_at";
        public static final String MODIFIED_DATE = "modified_at";
        public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    }
}
