<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<h3>
		Xin chào,
		<c:choose>
			<c:when test="${not empty sessionScope.account}">
                ${sessionScope.account.fullName}
            </c:when>
			<c:otherwise>
                Khách
            </c:otherwise>
		</c:choose>
		!
	</h3>
	<p>
		<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
	</p>
</body>
</html>
