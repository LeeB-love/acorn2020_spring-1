<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<a href="${pageContext.request.contextPath }/users/loginform.do">로그인</a>
			<a href="${pageContext.request.contextPath }/users/signup_form.do">회원가입</a>
		</c:when>
		<c:otherwise>
			<strong>${id }</strong>님 환영합니다.
			<a href="${pageContext.request.contextPath }/users/logout.do">로그아웃</a>
			<a href="${pageContext.request.contextPath }/users/private/info.do">마이페이지</a>
		</c:otherwise>
	</c:choose>
	<h1>인덱스 페이지입니다.</h1>
	<p>Spring Framework 동작중...</p>
	<ul>
		<li><a href="file/list.do">자료실 목록 보기</a></li>
		<li><a href="cafe/list.do">카페글 목록 보기</a></li>
	</ul>
</div>
</body>
</html>
