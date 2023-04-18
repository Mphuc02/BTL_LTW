    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- tieu de -->
<div id="tieude">
    <div class="tieude__link">
        <a class="" href="/">BlogTruyen.vn</a>
    </div>

    <c:set var="role" value="${USER_MODEL.role}" />
    <c:if test="${role eq 'ADMIN'}" >
        <ul>
            <li>
                <a href="${categories_page}">Quản lý thể loại</a>
            </li>
            <li>
                <a href="${blogs_page}">Quản lý bải viết</a>
            </li>
            <li>
                <a href="${users_page}">Quản lý người dùng</a>
            </li>
        </ul>
    </c:if>

    <div class="tieude__nut">
        <a class="tieude__nut--icon" href="">
            <i class="search-icon ti-announcement"></i> Thông báo</a>
        <a class="tieuDe__nut--icon" href="">
            <i class="search-icon ti-help-alt"></i> Trợ giúp</a>
        <a class="tieuDe__nut--icon" href="/create-blog">
            <i class="search-icon ti-write"></i> Viết truyện</a>
        <a class="tieuDe__nut--icon" href="login">Đăng nhập</a>
    </div>
</div>
<!-- end  tieu de -->