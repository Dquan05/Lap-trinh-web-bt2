<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
    <c:when test="${empty sessionScope.account}">
        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a> |
        <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/member/myaccount">
            ${sessionScope.account.fullName}
        </a> |
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </c:otherwise>
</c:choose>
