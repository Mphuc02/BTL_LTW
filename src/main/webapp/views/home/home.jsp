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
    <link rel="stylesheet" href="/assets/css/home6.css">
    <link rel="stylesheet" href="/assets/css/font/themify-icons-font/themify-icons/themify-icons.css">
</head>

<body>
    <div class="web">

        <!-- tieu de -->
        <div id="tieude">
            <div class="tieude__link">
                <a class="" href="">BlogTruyen.vn</a>
            </div>

            <div class="tieude__nut">
                <a class="tieude__nut--icon" href="">
                    <i class="search-icon ti-announcement"></i> Thông báo</a>
                <a class="tieuDe__nut--icon" href="">
                    <i class="search-icon ti-help-alt"></i> Trợ giúp</a>
                <a class="tieuDe__nut--icon" href="">
                    <i class="search-icon ti-write"></i> Viết truyện</a>
                <a class="tieuDe__nut--icon" href="login">Đăng nhập</a>
            </div>
        </div>
        <!-- end  tieu de -->

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
                                    <h3>sonanh2001</h3>
                                    <h3>${o.createAt}</h3>
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
        <jsp:include page="footer.jsp"></jsp:include>

        <!-- end footer -->

        <!-- end footer -->


    </div>
    <script>
        function initPagination(){
            var ulElement = document.querySelector("#pagination");
            var resultArr = ['1'];
            var resultStr = ''
            var currentPage = ${pageable.getPage()}
            var maxPage = ${pageable.getTotalPages()}

            if(currentPage - 1 > 2)
                resultArr.push('...')

            for(var i = 2; i < maxPage; i++){
                if(Math.abs(i - currentPage) < 2 && Math.abs(i - currentPage) >= 0)
                    resultArr.push(i+'')
            }

            console.log(currentPage)

            if(maxPage - currentPage > 2)
                resultArr.push('...')
            if(maxPage > 1)
                resultArr.push(maxPage+'')

            resultArr.forEach( (e) => resultStr += '<li class="page-item active">' + '<a class="page-link" href="/?page=' + e + '">' + e + '</a>' + '</li>')
            ulElement.innerHTML = resultStr
        }

        //Khởi tạo phân trang
        initPagination();
    </script>
</body>

</html>