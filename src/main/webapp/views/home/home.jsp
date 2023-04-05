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
    <link rel="stylesheet" href="css/trangchu.css">
    <link rel="stylesheet"
          href="font/themify-icons-font/themify-icons/themify-icons.css">
</head>

<body>
<div class="web">
    <header id="tieuDe">
        <div class="tieuDe_noiDung">
            <div class="tieuDe__link">
                <a class="tieuDe__button" href="">BlogTruyen.vn</a>
            </div>

            <div class="tieuDe__action">
                <a class="tieuDe__button" href=""> <i
                        class="search-icon ti-announcement"></i> Thông báo
                </a> <a class="tieuDe__button" href=""> <i
                    class="search-icon ti-help-alt"></i> Trợ giúp
            </a> <a class="tieuDe__button" href=""> <i
                    class="search-icon ti-write"></i> Viết truyện
            </a> <a class="tieuDe__button" href="/login">Đăng nhập</a>
            </div>
        </div>

    </header>
</div>

<div id="dieuHuong">
    <ul id="chiTiet">
        <li>
            <div>
                Thể loại <i class="ti-angle-down"></i>
            </div>
            <ul class="chiTiet__tLoai">
                <li><a href="">Cổ tích</a></li>
                <li><a href="">Truyện cười</a></li>
                <li><a href="">Đời sống</a></li>
                <li><a href="">Tình cảm</a></li>
                <li><a href="">Ngụ ngôn</a></li>
            </ul>



        </li>

        <li><a href="">Tác giả</a></li>

    </ul>

    <form  action="search" method="post">
        <div class="timkiem">
            <input class="noiDung_timKiem" type="text"
                   placeholder="Tìm kiếm truyện" name="keyword">
            <button class="btn-tKiem" type="submit">Tìm kiếm</button>
        </div>
    </form>
</div>

<!-- start content -->
<div id="noiDungTruyen">
    <div class="truyen__danhSach">
        <c:forEach items="${listA}" var="o">
            <div class="truyen1">
                <div class="truyen1__noiDung">
                    <a href="" class="noidung">
                        <div>
                            <h1>${o.ten_truyen}</h1>
                            <h3>sonanh2001</h3>
                            <h3>${o.time }</h3>
                        </div>
                    </a>
                </div>


                <div class="truyen__anh">
                    <img
                            src="https://tse3.mm.bing.net/th?id=OIP.PqG8_JFVxNv5W1iJ6A9YaAHaFA&pid=Api&P=0"
                            alt="">
                </div>
            </div>
        </c:forEach>

        <!-- trang khac -->
        <div class="truyen_trangKhac">
            <ul>
                <c:if test="${tag>1}">
                    <li><a href="home?index=${tag-1}">Previous</a></li>

                </c:if>
                <c:forEach begin="1" end="${endP}" var="i">
                    <li><a  class = "${tag == i?"active":""}" href="home?index=${i}">${i }</a></li>
                </c:forEach>
                <c:if test="${tag<endP}">
                    <li><a href="home?index=${tag+1}">Next</a></li>

                </c:if>


            </ul>
        </div>

    </div>

    <!-- Danh sach truyen -->

    <div class="truyen__moinhat">
        <h2 class="truyen__tieuDe">Truyện mới nhất</h2>
        <ul class="truyen__moinhat-ds">
            <li class="phanTu"><a href="/blog/view/28"
                                  class="truyenmoinhat__duongdan">
                <p class="truyen__icon">
                    Gửi bạn từng thân của tôi <i class="ti-angle-right"></i>
                </p>

            </a></li>
            <li class="phanTu"><a href="/blog/view/28"
                                  class="truyenmoinhat__duongdan">
                <p class="truyen__icon">
                    Gửi bạn tôi <i class="ti-angle-right"></i>
                </p>

            </a></li>
        </ul>
    </div>

</div>
<!-- end content -->


<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- end footer -->

</body>

</html>