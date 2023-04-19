<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.btl_web.dto.CategoryDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/home2.css">
    <link rel="stylesheet" href="/assets/css/font/themify-icons-font/themify-icons/themify-icons.css">
</head>

<body>
    <div class="web">
        <jsp:include page="/views/common/header.jsp" />

        <!-- dieu huong -->
        <div id="dieuhuong">
            <ul class="dieuhuong__ctiet">
                <li>
                    <div>
                        Thể loại
                        <i class="ti-angle-down"></i>
                    </div>
                    <ul class="dieuhuong__ctiet--tloai">
                        <li><a href="">Cổ tích</a></li>
                        <li><a href="">Truyện cười</a></li>
                        <li><a href="">Đời sống</a></li>
                        <li><a href="">Tình cảm</a></li>
                        <li><a href="">Ngụ ngôn</a></li>
                    </ul>
                </li>

                <li><a href="">Tác giả</a></li>
            </ul>

            <form action="search" method="post">
                <div class="dieuhuong__tkiem">
                    <input class="dieuhuong__tkiem--input" type="text" placeholder="Tìm kiếm truyện" name="keyword">
                    <button class="dieuhuong__tkiem--nut" type="submit">Tìm kiếm</button>
                </div>
            </form>
        </div>

        <!-- end dieu huong -->

        <!-- noi dung chinh cua truyen -->
        <div id="ndtruyen">
            <div class="ndtruyen__dsach">
                <c:forEach items="${listA}" var="o">
                    <div class="truyen1">
                        <div class="truyen1__ndung">
                            <a href="" class="noidung">
                                <div>
                                    <h1>${o.title}</h1>
                                    <h3>${o.user.fullName}</h3>
                                    <h3>${o.createdAt}</h3>
                                </div>
                            </a>
                        </div>

                        <div class="truyen1__anh">
                            <img src="https://tse3.mm.bing.net/th?id=OIP.PqG8_JFVxNv5W1iJ6A9YaAHaFA&pid=Api&P=0" alt="">
                        </div>
                    </div>
                </c:forEach>


                <!-- trang khac -->
                <div class="card-footer">
                    <nav class="Page navigation">
                        <ul class="pagination jc-center" id="pagination"></ul>
                    </nav>
                </div>
            </div>

            <!-- Danh sach truyen -->

            <div class="truyen__moinhat">
                <h2 class="truyen__tieuDe">
                    Truyện mới nhất
                </h2>
                <ul class="truyen__moinhat-ds">
                    <li class="phanTu">
                        <a href="/blog/view/28" class="truyenmoinhat__duongdan">
                            <p class="truyen__icon">
                                Gửi bạn từng thân của tôi
                                <i class="ti-angle-right"></i>
                            </p>

                        </a>
                    </li>
                    <li class="phanTu">
                        <a href="/blog/view/28" class="truyenmoinhat__duongdan">
                            <p class="truyen__icon">
                                Gửi bạn tôi
                                <i class="ti-angle-right"></i>
                            </p>

                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- end noi dung truyen -->

        <!-- start footer -->

        <!-- footer -->
        <jsp:include page="../common/footer.jsp"></jsp:include>

        <!-- end footer -->

        <!-- end footer -->


    </div>
    <jsp:include page="/assets/javascript/pagination.jsp" />
    <script>
        initPagination('${home}')
    </script>
</body>

</html>