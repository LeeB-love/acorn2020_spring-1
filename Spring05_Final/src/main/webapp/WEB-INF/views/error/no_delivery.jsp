<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/error/no_delivery.jsp</title>
</head>
<body>
<div class="container">
	<h1>배송 관련 오류가 발생했습니다.</h1>
	<p class="alert alert-danger">
		<strong>${exception.message }</strong>
		배송 기사가 회복하면 다시 연락드릴게요 죄송함돠 (_ _))
		<a class="alert-link" href="${pageContext.request.contextPath }">홈으로</a>
	</p>
</div>
</body>
</html>