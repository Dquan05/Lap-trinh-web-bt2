<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>Đăng nhập</h2>

	<div style="color: red;">
		<c:if test="${not empty alert}">
            ${alert}
        </c:if>
	</div>

	<form action="${pageContext.request.contextPath}/login" method="post">
		<div>
			<label>Tài khoản</label> <input type="text" name="username" />
		</div>
		<div>
			<label>Mật khẩu</label> <input type="password" name="password" />
		</div>
		<div>
			<label><input type="checkbox" name="remember"> Ghi
				nhớ</label>
		</div>
		<div>
			<button type="submit">Đăng nhập</button>
		</div>
	</form>
</body>
</html>
