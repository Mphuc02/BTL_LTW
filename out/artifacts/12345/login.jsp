<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <%--Bắt trình duyệt phải tải lại trang css--%>

    <title>Đăng nhập</title>
    <link rel="stylesheet" href="assets/css/login/login.css">
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
                    <h2>Đăng nhập</h2>
                    <div class="message ${message_type} ${display_flex}">
                        <div class="message-title">
                            ${message}
                        </div>
                    </div>
                    <div class="inputbox">
                        <input name="userName" type="text" required>
                        <label>Tên đăng nhập</label>
                    </div>
                    <div class="inputbox">
                        <input name="passWord" type="password" required>
                        <label>Mật khẩu</label>
                    </div>
                    <button>Đăng nhập</button>
                    <div class="register">
                        <p>Bạn chưa có tài khoản ? <a href="registration">Đăng kí ngay</a></p>
                    </div>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>
