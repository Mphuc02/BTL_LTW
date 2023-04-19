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
        <div class="tieude__nut">
            <div>
                <a class="tieude__nut--icon" href="">
                    <i class="search-icon ti-announcement"></i>
                    Thông báo</a>
            </div>
            <div>
                <a class="tieuDe__nut--icon" href="">
                    <i class="search-icon ti-help-alt"></i>
                    Trợ giúp</a>
            </div>
            <div>
                <a class="tieuDe__nut--icon" href="/create-blog">
                    <i class="search-icon ti-write"></i>
                    Viết truyện</a>
            </div>
            <c:if test="${empty USER_MODEL}" >
                <div>
                    <a class="tieuDe__nut--icon" href="/login">
                        <i class="ti-layout-grid2-alt"></i>
                        Đăng nhập</a>
                </div>
            </c:if>
        </div>

        <c:if test="${not empty USER_MODEL}" >
            <div class="avatar">
                <img src="https://tse3.mm.bing.net/th?id=OIP.2qeEPUZpvbhuqtsQ_XVRKwHaJN&pid=Api&P=0" alt="Avatar"
                     class="avatar_anh" onclick="showMenu()">
                <div class="avatar_ndung">
                    <ul class="avatar_menu" id="menu">
                        <li>
                            <a href="#">
                                <i class="avatar_icon ti-user"></i>
                                Cá nhân</a>
                        </li>
                        <li>
                            <a href="#">
                                <i class=" avatar_icon ti-settings"></i>
                                Cài đặt</a>
                        </li>
                        <li>
                            <a href="#">
                            <i class="avatar_icon ti-help-alt"></i>
                            Trợ giúp</a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="avatar_icon ti-info-alt"></i>
                                Phản hồi</a>
                        </li>
                        <li>
                            <a href="/login?action=logout">
                                <i class="avatar_icon ti-drupal"></i>
                                Đăng xuất</a>
                        </li>
                    </ul>

                </div>
            </div>
        </c:if>

    </div>
    <script>
        function showMenu() {
            var menu = document.getElementById("menu");
            if (menu.style.display === "block") {
                menu.style.display = "none";
            } else {
                menu.style.display = "block";
            }
        }
    </script>
</div>
<!-- end  tieu de -->