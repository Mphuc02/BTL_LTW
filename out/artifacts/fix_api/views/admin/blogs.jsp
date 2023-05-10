<%@ page import="com.example.btl_web.constant.Constant" %>
<%@ page import="com.example.btl_web.dto.BlogDto" %>
<%@ page import="com.example.btl_web.paging.Pageable" %>
<%@ page import="com.example.btl_web.paging.PageRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.btl_web.service.BlogService" %>
<%@ page import="com.example.btl_web.configuration.ServiceConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="api_url" value="/api-blog"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/assets/css/admin/admin.css">
    <link rel="stylesheet" href="/assets/css/home7.css">
    <title>Admin</title>
</head>
<body>
    <%
        BlogService blogService = ServiceConfiguration.getBlogService();

        StringBuilder pageUrl = new StringBuilder(Constant.Admin.BLOGS_PAGE + "?");

        BlogDto searchDto = null;
        String searchName = request.getParameter("keySearch");
        if(searchName != null)
        {
            searchDto = new BlogDto();
            searchDto.setTitle(searchName);
            pageUrl.append("keySearch=" + searchName + "&");
        }
        pageUrl.append("page=");

        long totalBlog = blogService.countBlogs(searchDto);
        Pageable pageable = new PageRequest(request.getParameterMap(), totalBlog);

        List<BlogDto> blogList = blogService.getAllBlogs(pageable, searchDto);

        request.setAttribute("pageable", pageable);
        request.setAttribute("blogList", blogList);
        request.setAttribute("categories_page", Constant.Admin.CATEGORIES_PAGE);
        request.setAttribute("home", pageUrl.toString());
        request.setAttribute("users_page", Constant.Admin.USERS_PAGE);
        request.setAttribute("keySearch", searchName);
    %>
    <div id="Admin">
        <div class="navbar-main">
            <jsp:include page="/views/common/header.jsp" />
        </div>
        <div id="main">
            <!-- MAIN -->
            <div class="container">
                <div class="navbar">
                    <div class="icon--link">
                        <i class="icon fa-solid fa-book-open"></i>
                    </div>
                </div>
            </div>
            <p class="${status}">${message}</p>
            <!-- Content -->
            <section class="content">
                <div class="row mt-5">
                    <div class="col">
                        <div class="card">
                            <div class="card-header">
                                <div class="float-left">
                                    <form action="/admin/blogs" method="post">
                                        <div class="tim-kiem">
                                            <input type="text" placeholder="Tìm Kiếm" class="search" name="keySearch" value="${keySearch}">
                                            <button class="btn btn-search">Tìm kiếm</button>
                                        </div>
                                    </form>

                                    <c:if test="${not empty keySearch}" >
                                        <p>Tìm kiếm truyện theo từ khoá: ${keySearch}</p>
                                    </c:if>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table" style="border: solid 1px #000;">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên</th>
                                        <th>Người đăng</th>
                                        <th>Thời gian đăng</th>
                                        <th>Lượt thích</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="blog" items="${blogList}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${blog.title}</td>
                                                <td>${blog.user.userId}</td>
                                                <td>${blog.createdAt}</td>
                                                <td>0</td>
                                                <td>
                                                    <c:if test="${blog.status == 0}"><p class="blog-status-${loop.index} action" onclick="showOption(${loop.index})">Đã bị ẩn</p></c:if>
                                                    <c:if test="${blog.status == 1}"><p class="blog-status-${loop.index} action" onclick="showOption(${loop.index})">Đã được đuyệt</p></c:if>
                                                    <c:if test="${blog.status == 2}"><p class="blog-status-${loop.index} action" onclick="showOption(${loop.index})">Đang chờ xét duyệt</p></c:if>

                                                    <div class="menu-option">
                                                        <ul class="menu-list"  id="menu-${loop.index}">
                                                            <li>
                                                                <a href="/views/admin/sendApi/blog.jsp?id=${blog.blogId}&status=0">Ẩn truyện này</a>
                                                            </li>
                                                            <li>
                                                                <a href="/views/admin/sendApi/blog.jsp?id=${blog.blogId}&status=1">Công khai truyện này</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="card-footer">
                                <ul class="pagination jc-center" id="pagination">
                                    <jsp:include page="/views/common/pagingation.jsp" />
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</body>
</html>