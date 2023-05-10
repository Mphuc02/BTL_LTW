<%@ page import="com.example.btl_web.constant.Constant" %>
<%@ page import="com.example.btl_web.dto.UserDto" %>
<%@ page import="com.example.btl_web.paging.Pageable" %>
<%@ page import="com.example.btl_web.paging.PageRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.btl_web.service.UserService" %>
<%@ page import="com.example.btl_web.configuration.ServiceConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="api_url" value="/api-admin-user" />
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
        UserService userService = ServiceConfiguration.getUserService();

        StringBuilder pageUrl = new StringBuilder(Constant.Admin.USERS_PAGE + "?");

        UserDto searchDto = null;

        String searchName = request.getParameter("searchName");
        if(searchName != null)
        {
            searchDto = new UserDto();
            searchDto.setFullName(searchName);
            pageUrl.append("searchName=" + searchName + "&");
        }
        pageUrl.append("page=");

        long totalItem = userService.countUsers(searchDto);
        Pageable pageable = new PageRequest(request.getParameterMap(), totalItem);

        List<UserDto> dtos = userService.findAll(pageable, searchDto);

        request.setAttribute("users_list", dtos);
        request.setAttribute("pageable", pageable);
        request.setAttribute("categories_page", Constant.Admin.CATEGORIES_PAGE);
        request.setAttribute("blogs_page", Constant.Admin.BLOGS_PAGE);
        request.setAttribute("home", pageUrl.toString());
        request.setAttribute("searchName", searchName);
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
                                    <form action="" method="get">
                                        <div class="tim-kiem">
                                            <input type="text" placeholder="Tìm Kiếm" class="search" name="searchName" value="${searchName}">
                                            <button class="btn btn-search">Tìm kiếm</button>
                                        </div>
                                    </form>

                                    <c:if test="${not empty searchName}">
                                        <p>Tìm kiếm các tài khoản theo từ khoá: ${searchName}</p>
                                    </c:if>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table" style="border: solid 1px #000;">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>STT</th>
                                        <th>Id</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Cấp bậc</th>
                                        <th>Truyện đã viết</th>
                                        <th>Thời gian tạo</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${users_list}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index+1}</td>
                                                <td>${user.userId}</td>
                                                <td>${user.fullName}</td>
                                                <td>${user.email}</td>
                                                <td>${user.role}</td>
                                                <td>1</td>
                                                <td>
                                                    <p>${user.registeredAt}</p>
                                                </td>
                                                <td>
                                                    <c:if test="${user.status == 1}" >
                                                        <p class="blog-status-${loop.index} action" onclick="showOption(${loop.index})">Đand hoạt động</p>
                                                    </c:if>
                                                    <c:if test="${user.status == 0}" >
                                                        <p class="blog-status-${loop.index} action" onclick="showOption(${loop.index})">Đã bị khóa</p>
                                                    </c:if>

                                                    <div class="menu-option">
                                                        <ul class="menu-list"  id="menu-${loop.index}">
                                                            <li>
                                                                <a href="/views/admin/sendApi/user.jsp?id=${user.userId}&status=0">Khóa tài khoản này</a>
                                                            </li>
                                                            <li>
                                                                <a href="/views/admin/sendApi/user.jsp?id=${user.userId}&status=1">Mở khóa tài khoản này</a>
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
                                <nav class="Page navigation">
                                    <ul class="pagination jc-center" id="pagination">
                                        <jsp:include page="/views/common/pagingation.jsp" />
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</body>
</html>