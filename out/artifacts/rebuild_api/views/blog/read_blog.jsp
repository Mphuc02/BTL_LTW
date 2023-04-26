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
    <link rel="stylesheet" href="/assets/css/home4.css">
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

            <p>${blog.content}</p>

            <h4 style="display: flex; justify-content: center;">---End---</h4> <br>

            <!-- <h4>Bình luận về truyện</h4>
            <textarea name="comment" id="comment" cols="30" rows="10"></textarea>
            <h5>Nội dung không được để trống</h5>
            <input type="submit" value="Đăng" id="submit">

            <div id="cm-comment">
                <ul id="cm">
                    <ul id="cm-one">0</ul>
                    <ul id="cm-one">Bình luận</ul>
                </ul>
            </div> -->
        </div>

        <div class="sidebar">

            <div class="sidebar-two">
                <!-- Phần hiển thị bình luận -->
                <div class="comments">
                    <div class="comment">
                        <div class="author">Nguyễn Văn A</div>
                        <div class="text">Bình luận của Nguyễn Văn A</div>
                    </div>
                    <div class="comment">
                        <div class="author">Trần Thị B</div>
                        <div class="text">Bình luận của Trần Thị B</div>
                    </div>
                    <div class="comment">
                        <div class="author">Lê Văn C</div>
                        <div class="text">Bình luận của Lê Văn C</div>
                    </div>
                </div>

                <!-- Form để thêm bình luận -->
                <div class="comment-form">
                    <h3>Thêm bình luận</h3>
                    <label for="comment">Bình luận:</label>
                    <textarea id="comment" name="comment" required></textarea>

                    <button onclick="sendComment()">Gửi</button>
                </div>

            </div>
        </div>
    </div>

    <jsp:include page="/assets/javascript/create_or_update_api.jsp" />
    <script>
        function likeBlog(blogId, statusLike)
        {
            if(statusLike == 1)
                var method = 'POST'
            else if(statusLike == 0)
                var method = 'DELETE'
            var data = {
                blogId: blogId
            }

            formSubmit(data, '${api_url_like}', method, function (errors, status){
                if(status == 200){
                    alert("Đã like bài viết này!")
                    resetLikeButton(statusLike)
                }
                else{
                    alert("Bạn phải đăng nhập thì mới xem được bài viết này")
                }
            })
        }

        function resetLikeButton(status){
            var likeButton = document.querySelector("#like-button");
            if(status == 1){
                likeButton.outerHTML = '<button id="like-button" onclick="likeBlog(${blog.blogId},0)">Bỏ thích bài viết này</button>'
            }
            else {
                likeButton.outerHTML = '<button id="like-button" onclick="likeBlog(${blog.blogId},1)">Thích bài viết này</button>'
            }
        }

        function sendComment(){
            var commentContent = document.querySelector("#comment").value
            if(!commentContent)
                return
            var data = {
                content: commentContent,
                blogComment: ${blog.blogId}
            }
            formSubmit(data, '${api_url_comment}', 'POST', function (errors, status){

            })
        }
    </script>
    <jsp:include page="/views/common/footer.jsp" />
</body>
</html>