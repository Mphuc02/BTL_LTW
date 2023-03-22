<%@page pageEncoding="UTF-8" contentType="text/html; ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="../../assets/css/login/login.css">
  <title>Blog Truyện - Đăng nhập</title>
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
                    <form action="">
                        <h2>Đăng nhập</h2>
                        <div style="background-color: brown;"  class="message">
                            <div class="message-title">
                                Tài khoản hoặc mật khẩu không chính xác
                            </div>
                        </div>
                        <div style="background-color: rgb(113, 180, 197);" class="message-2">
                            <div class="message-title-2">
                                Bạn đã đăng xuất khỏi trái đất
                            </div>
                        </div>
                        <div class="inputbox">
                            <input type="text" required>
                            <label>Tên đăng nhập</label>
                        </div>
                        <div class="inputbox">
                            <input type="password" required>
                            <label>Mật khẩu</label>
                        </div>
                        <button>Đăng nhập</button>
                        <div class="register">
                            <p>Bạn chưa có tài khoản ? <a href="http://127.0.0.1:5500/registraion/registraion.html">Đăng kí ngay</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
</body>
</html>