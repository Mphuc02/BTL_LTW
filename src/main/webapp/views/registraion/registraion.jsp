<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="../.././assets/css/registraion/registraion.css">
  <title>Blog Truyện - Đăng kí</title>
</head>
<body>
    <div class="main">
        <div class="header">
            <div class="header__navbar">
                <a href="#" class="header__navbar--main">
                    BlogTruyen.vn
                </a>
            </div>
        </div>
        <section>
            <div class="form-box">
                <div class="form-value">
                    <form action="" method="post">
                        <h2>Đăng kí</h2>
                        <div class="inputbox">
                            <input type="text" name="userName" required>
                            <label>Tên đăng nhập</label>
                        </div>
                        <span class="bug">${bug1}</span>
                        <div class="inputbox">
                            <input type="password" name="passWord" required>
                            <label>Mật khẩu</label>
                        </div>
                        <span class="bug">${bug2}</span>
                        <div class="inputbox">
                            <input type="password" name="passWord-2" required>
                            <label>Nhập lại mật khẩu</label>
                        </div>
                        <span class="bug">${bug3}</span>
                        <div class="inputbox">
                            <input type="mail" name="email" required>
                            <label>Email</label>
                        </div>
                        <span class="bug">${bug4}</span>
                        <button>Đăng ký</button>
                        <div class="register">
                            <p>Bạn đã có tài khoản rồi ? <a href="../../login">Đăng nhập</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
</body>
</html>