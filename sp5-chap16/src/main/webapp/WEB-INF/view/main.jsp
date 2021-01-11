<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>메인</title>
</head>
<body>
	<c:if test="${empty authInfo}">
	<p>환영합니다.</p>
	<p>
		<a href="<c:url value="/login" />">[<spring:message code="login.title"/>]</a>
		<a href="<c:url value="/register/step1" />">[<spring:message code="member.register"/>]</a>
	</p>
	</c:if>
	
	<c:if test="${! empty authInfo}">
	<p>${authInfo.name}님, 환영합니다.</p>
	<p>
		<a href="<c:url value="/edit/changePassword" />">[<spring:message code="change.pwd.title"/>]</a>
		<a href="<c:url value="/logout" />">[<spring:message code="logout"/>]</a>
	</p>
	</c:if>
</body>
</html>