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
    <link rel="stylesheet" href="../.././assets/css/admin/admin.css">
    <title>Admin</title>
</head>
<body>
    <div id="Admin">
        <div id="main">
            <!-- MAIN -->
            <div class="container">
                <div class="navbar">
                    <div class="icon--link">
                        <i class="icon fa-solid fa-book-open"></i>
                    </div>
                    <div class="navbar-main">
                        <ul class="navbar__list">
                            <li class="navbar__item">
                                <a href="#" class="navbar__item--link">Quản lý thể loại</a>
                            </li>
                            <li class="navbar__item">
                                <a href="/admin-home?page=${blogs}" class="navbar__item--link">Quản lý truyện</a>
                            </li>
                            <li class="navbar__item">
                                <a href="/admin-home?page=${users}" class="navbar__item--link">Quản lý người dùng</a>
                            </li>
                        </ul>
                        <button class="btn-exist">
                            <a href="/login" class="btn-exist--link">Đăng xuất</a>
                        </button>
                    </div>
                </div>
            </div>
            <!-- Content -->
            <section class="content">
                <div class="row mt-5">
                    <div class="col">
                        <div class="card">
                            <div class="card-header">
                                <div class="float-left">
                                    <form action="#">
                                        <div class="tim-kiem">
                                            <input type="text" placeholder="Tìm Kiếm" class="search">
                                            <button class="btn btn-search">
                                                <a href="#" class="title">Tìm Kiếm</a>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <div class="float-right">
                                    <a href="/admin/categories/edit/-1" class="btn btn-outline-primary">Thêm thể loại</a>
                                </div>
                            </div>
                            <div class="card-body">
                                <table class="table" style="border: solid 1px #000;">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Tên</th>
                                        <th>Thời gian tạo</th>
                                        <th>Số lượng Truyện</th>
                                        <th>Người thêm</th>
                                        <th>Trạng thái</th>
                                        <th>Chỉnh sửa</th>
                                        <th>Xoá</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${list}">
                                        <tr>
                                            <td>${item.categoryId}</td>
                                            <td>${item.name}</td>
                                            <td>${item.createdAt}</td>
                                            <td>1</td>
                                            <td>${item.userId}</td>
                                            <td>
                                                <c:if test="${item.status == 1}" >
                                                    Công khai
                                                    <c:set var="status" value="Ẩn thể loại này" />
                                                </c:if>
                                                <c:if test="${item.status == 0}" >
                                                    Đã ẩn
                                                    <c:set var="status" value="Hiện thể loại này"/>
                                                </c:if>
                                            </td>
                                            <td>
                                                <a href="/admin/categories/edit/${item.categoryId}">Chỉnh sửa</a>
                                            </td>
                                            <td>
                                                <div onclick="deleteCategory(${item.categoryId}, ${item.status})">${status}</div>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <div class="card-footer">
                                <nav class="Page navigation">
                                    <ul class="pagination jc-center" id="pagination">
                                        <%--<li class="page-item active">--%>
                                        <%--    <a href="#" class="page-link">First</a>--%>
                                        <%--</li>--%>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <script>
        function deleteCategory(categoryIdStr, statusInt){
            if(statusInt == 1)
                statusInt = 0
            else
                statusInt = 1

            // Tạo XMLHttpRequest
            var xhr = new XMLHttpRequest();

            var data = {
                categoryId: categoryIdStr,
                status: statusInt
            }

            // Thiết lập phương thức DELETE và URL của API để gửi JSON
            xhr.open("DELETE", '${api_url}', true);
            // Thiết lập header cho request
            xhr.setRequestHeader("Content-Type", "application/json");

            // Gửi đối tượng JSON
            xhr.send(JSON.stringify(data));

            // Xử lý response nếu cần
            xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    if(statusInt == 1)
                        alert("Đã công khai thể loại này!")
                    else
                        alert("Đã ẩn thể loại này!")
                    location.reload();//Tải lại trang
                }
            };
        }

        function initPagination(){
            var ulElement = document.querySelector("#pagination");
            var resultArr = ['1'];
            var resultStr = ''
            var currentPage = ${pageable.getPage()}
            var maxPage = ${pageable.getTotalPages()}

            for(var i = 2; i < maxPage; i++){
                if(currentPage - i > 2 || i - currentPage < 2)
                    resultArr.push(i+'')
            }

            if(maxPage > 1)
                resultArr.push(maxPage+'')
            resultArr.forEach( (e) => resultStr += '<li class="page-item active">' + '<a class="page-link" href="/admin/categories?page=' + e + '">' + e + '</a>' + '</li>')
            ulElement.innerHTML = resultStr
        }

        //Khởi tạo phân trang
        initPagination();
    </script>
</body>
</html>