<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="api_url_like" value="/api-create-like" />
<c:set var="api_url_comment" value="/api-create-comment" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/blog/blog1.css">
    <link rel="stylesheet" href="/assets/css/home7.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <title>Document</title>
</head>
<body>

    <jsp:include page="/views/common/header.jsp" />


    <div class="main">
        <div class="content">
            <h1 style="display: flex; justify-content: center;">${blog.title} </h1> <br> <br> <br>

            <p>Thể loại:</p>
            <c:forEach var="category" items="${blog.categories}" varStatus="loop" >
                <c:if test="${loop.index > 0}">
                    <span>-</span>
                </c:if>
                <span><a href="/?categorySearch=${category.categoryId}">${category.name}</a></span>
            </c:forEach>

            <c:if test="${not empty USER_MODEL}" >
                <c:if test="${blog.checkUserLikedBlog(USER_MODEL.userId) == false}" >
                    <button id="like-button" onclick="likeBlog(${blog.blogId},1)">Thích bài viết này</button>
                </c:if>
                <c:if test="${blog.checkUserLikedBlog(USER_MODEL.userId)}" >
                    <button id="like-button" onclick="likeBlog(${blog.blogId},0)">Bỏ thích bài viết này</button>
                </c:if>
            </c:if>

            <p>${blog.likedUsers.size()} lươt thích</p>
            
            <img src="${blog.imageTitle}">

            <p>${blog.content}</p>

            <h4 style="display: flex; justify-content: center;">---End---</h4> <br>
        </div>

        <div class="sidebar">

            <div class="sidebar-two">
                <h1>${blog.comments.size()} bình luận</h1>
                <!-- Form để thêm bình luận -->
                <div class="comment-form">
                    <h3>Thêm bình luận</h3>
                    <textarea id="comment" name="comment" required></textarea>

                    <button onclick="sendComment()">Gửi</button>
                </div>
                <!-- Phần hiển thị bình luận -->
                <div class="comments">
                    <c:forEach var="comment" items="${blog.comments}" >
                        <div class="comment">
                            <div class="author">${comment.userComment.fullName}</div>
                            <div class="text">${comment.content}</div>
                            <div>${comment.createdAt}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <script>
        //Todo: sửa lại cách gửi api 2 page này
    </script>
    <jsp:include page="/views/common/footer.jsp" />
</body>
</html>