<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="/assets/css/admin/admin1.css">
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
                            <a href="/admin-home?page=${categories}" class="navbar__item--link">Quản lý thể loại</a>
                        </li>
                        <li class="navbar__item">
                            <a href="#" class="navbar__item--link">Quản lý truyện</a>
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
                        </div>
                        <div class="card-body">
                            <table class="table" style="border: solid 1px #000;">
                                <thead class="thead-dark">
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Tác giả</th>
                                    <th>Lượt thích</th>
                                    <th>Chỉnh sửa</th>
                                </tr>
                                </thead>
                                <tbody><tr>
                                    <td>1</td>
                                    <td>Cây tre trăm đốt</td>
                                    <td>sonanh2001</td>
                                    <td>2</td>
                                    <td>
                                        <a href="#" class="btn btn-eye">
                                            <i class="fa-solid fa-eye"></i>
                                        </a>
                                        <a href="#" class="btn btn-erase">
                                            <i class="fa-solid fa-trash-can"></i>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Sự tích cây vú sữa</td>
                                    <td>sonanh2001</td>
                                    <td>2</td>
                                    <td>
                                        <a href="#" class="btn btn-eye">
                                            <i class="fa-solid fa-eye"></i>
                                        </a>
                                        <a href="#" class="btn btn-erase">
                                            <i class="fa-solid fa-trash-can"></i>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Tấm cám</td>
                                    <td>sonanh2001</td>
                                    <td>1</td>
                                    <td>
                                        <a href="#" class="btn btn-eye">
                                            <i class="fa-solid fa-eye"></i>
                                        </a>
                                        <a href="#" class="btn btn-erase">
                                            <i class="fa-solid fa-trash-can"></i>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Sự tích bông hoa cúc trắng</td>
                                    <td>sonanh2001</td>
                                    <td>1</td>
                                    <td>
                                        <a href="#" class="btn btn-eye">
                                            <i class="fa-solid fa-eye"></i>
                                        </a>
                                        <a href="#" class="btn btn-erase">
                                            <i class="fa-solid fa-trash-can"></i>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Sọ dừa</td>
                                    <td>sonanh2001</td>
                                    <td>1</td>
                                    <td>
                                        <a href="#" class="btn btn-eye">
                                            <i class="fa-solid fa-eye"></i>
                                        </a>
                                        <a href="#" class="btn btn-erase">
                                            <i class="fa-solid fa-trash-can"></i>
                                        </a>
                                    </td>
                                </tr></tbody>
                            </table>
                        </div>
                        <div class="card-footer">
                            <nav class="Page navigation">
                                <ul class="pagination jc-center">
                                    <li class="page-item active">
                                        <a href="#" class="page-link">First</a>
                                    </li>
                                    <li class="page-item active">
                                        <a href="#" class="page-link">1</a>
                                    </li>
                                    <li class="page-item">
                                        <a href="#" class="page-link">2</a>
                                    </li>
                                    <li class="page-item">
                                        <a href="#" class="page-link">3</a>
                                    </li>
                                    <li class="page-item">
                                        <a href="#" class="page-link">4</a>
                                    </li>
                                    <li class="page-item">
                                        <a href="#" class="page-link">5</a>
                                    </li>
                                    <li class="page-item">
                                        <a href="#" class="page-link">Last</a>
                                    </li>
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