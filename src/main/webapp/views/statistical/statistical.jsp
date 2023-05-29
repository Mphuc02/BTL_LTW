<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="parameter" value="" />
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="../../assets/css/statistical/statistical.css">
  <link rel="stylesheet" href="./themify-icons/themify-icons.css">
</head>

<body>

<div class="web">
  <jsp:include page="/views/common/header.jsp" />

  <!-- Start nội dung -->
  <div id="noidung">
    <div class="truyen__moinhat">
      <h2 class="truyen__tieuDe">
        Truyện nhiều like nhất
      </h2>
      <ul class="truyen__moinhat-ds">
        <li class="deMuc">
          <h3>Tên truyện</h3>
          <h3 class="soluong">Số lượng</h3>
        </li>
        <li class="phanTu">
          <a href="/blog/view/28" class="truyenmoinhat__duongdan">
            <p class="truyen__icon">
              Gửi bạn tôi
              <i>yujhvhjgvfhcyvgvgc</i>
            </p>

          </a>
        </li>
      </ul>
    </div>

    <div class="truyen__moinhat">
      <h2 class="truyen__tieuDe">
        Truyện nhiều comment nhất
      </h2>
      <ul class="truyen__moinhat-ds">
        <li class="deMuc">
          <h3>Tên truyện</h3>
          <h3 class="soluong">Số lượng</h3>
        </li>
        <li class="phanTu">
          <a href="/blog/view/28" class="truyenmoinhat__duongdan">
            <p class="truyen__icon">
              Gửi bạn tôi
              <i>yujhvhjgvfhcyvgvgc</i>
            </p>

          </a>
        </li>
      </ul>


    </div>

    <div class="truyen__moinhat">
      <h2 class="truyen__tieuDe">
        Thể loại nhiều truyện nhất
      </h2>
      <ul class="truyen__moinhat-ds">
        <li class="deMuc">
          <h3>Tên truyện</h3>
          <h3 class="soluong">Số lượng</h3>
        </li>
        <li class="phanTu">
          <a href="/blog/view/28" class="truyenmoinhat__duongdan">
            <p class="truyen__icon">
              Gửi bạn tôi
              <i>yujhvhjgvfhcyvgvgc</i>
            </p>

          </a>
        </li>
      </ul>
    </div>

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

<%--  <jsp:include page="../common/footer.jsp"></jsp:include>--%>
</div>
</body>