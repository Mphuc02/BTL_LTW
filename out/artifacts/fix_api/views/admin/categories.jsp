<%@ page import="com.example.btl_web.constant.Constant" %>
<%@ page import="com.example.btl_web.dto.CategoryDto" %>
<%@ page import="com.example.btl_web.paging.Pageable" %>
<%@ page import="com.example.btl_web.paging.PageRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.btl_web.service.CategoryService" %>
<%@ page import="com.example.btl_web.configuration.ServiceConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="api_url" value="/api-admin-category" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/assets/css/admin/admin.css">
    <link rel="stylesheet" href="/assets/css/home.css">
    <title>Admin</title>
</head>
<body>
    <%
        CategoryService categoryService = ServiceConfiguration.getCategoryService();

        StringBuilder pageUrl = new StringBuilder(Constant.Admin.CATEGORIES_PAGE + "?");

        CategoryDto searchDto = null;
        String searchName = request.getParameter("search-category");
        if(searchName != null)
        {
            searchDto = new CategoryDto();
            searchDto.setName(searchName);
            pageUrl.append("search-category=" + searchName + "&");
        }
        pageUrl.append("page=");

        long totalCategories = categoryService.countCategories(searchDto);
        Pageable pageable = new PageRequest(request.getParameterMap(), totalCategories );
        request.setAttribute("pageable", pageable);
        List<CategoryDto> categoryDtos = categoryService.findAll(pageable, searchDto);

        request.setAttribute("list", categoryDtos);
        request.setAttribute("home", pageUrl.toString());
        request.setAttribute("blogs_page", Constant.Admin.BLOGS_PAGE);
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
                                    <form action="" method="get">
                                        <div class="tim-kiem">
                                            <input type="text" placeholder="Tìm Kiếm" class="search" name="search-category" value="${keySearch}">
                                            <button class="btn btn-search">Tìm kiếm</button>
                                        </div>
                                    </form>
                                    <c:if test="${not empty keySearch}" >
                                        <p>Tìm kiếm thể loại theo từ khoá: ${keySearch}</p>
                                    </c:if>
                                </div>
                                <div class="float-right">
                                    <a href="/admin/categories/edit/-1" class="btn btn-outline-primary">Thêm thể loại</a>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table" style="border: solid 1px #000;">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>Stt</th>
                                        <th>Id</th>
                                        <th>Tên</th>
                                        <th>Thời gian tạo</th>
                                        <th>Số lượng Truyện</th>
                                        <th>Người thêm</th>
                                        <th>Trạng thái</th>
                                        <th>Chỉnh sửa</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${list}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${item.categoryId}</td>
                                            <td>${item.name}</td>
                                            <td>${item.createdAt}</td>
                                            <td>1</td>
                                            <td>${item.userId}</td>
                                            <td>
                                                <c:if test="${item.status == 1}" >
                                                    <p class="blog-status-${loop.index} action" onclick="showOption(${loop.index})">Công khai</p>
                                                </c:if>
                                                <c:if test="${item.status == 0}" >
                                                    <p class="blog-status-${loop.index} action" onclick="showOption(${loop.index})">Đã ẩn</p>
                                                </c:if>

                                                <div class="menu-option">
                                                    <ul class="menu-list"  id="menu-${loop.index}">
                                                        <li>
                                                            <a href="/views/admin/sendApi/category.jsp?id=${item.categoryId}&status=0">Ẩn thể loại</a>
                                                        </li>
                                                        <li>
                                                            <a href="/views/admin/sendApi/category.jsp?id=${item.categoryId}&status=1">Công khai thể loại này</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="/admin/categories/edit/${item.categoryId}">Chỉnh sửa</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="card-footer">
                                <ul class="pagination jc-center" id="pagination">
                                    <jsp:include page="/views/common/pagingation.jsp"/>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <jsp:include page="/views/common/footer.jsp" />
</body>
</html>