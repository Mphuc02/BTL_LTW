<%@page pageEncoding="UTF-8" contentType="text/html; ISO-8859-1" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form th:action="login" method="post" class="p-3 form__control">
        <p>${message}</p>
        <div class="form-group">
            <label for="username">Tên đăng nhập</label>
            <input type="text" name="username" id="username" class="form-control" placeholder="Tên đăng nhập" aria-describedby="helpId">
        </div>
        <div class="form-group">
            <label for="password">Mật khẩu</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Mật khẩu" aria-describedby="helpId">
        </div>
        <div class="form__request">
            <span class="mr-2">Bạn chưa có tài khoản?</span>
            <a href="registration">Đăng kí ngay</a>
        </div>
        <button class="btn btn-primary mt-2">
            Đăng nhập
        </button>
    </form>

</body>
</html>