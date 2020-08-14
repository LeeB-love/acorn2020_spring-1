<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/private/pwd_update.jsp</title>
</head>
<body>
	<c:choose>
		<c:when test="${isSuccess }">
			<p>비밀번호 변경이 완료되었습니다. <a href="info.do">확인</a></p>
		</c:when>
		<c:otherwise>
			<p>이전 비밀번호가 일치하지 않습니다. 다시 확인해주십시오. <a href="pwd_updateform.do">다시 시도</a></p>
		</c:otherwise>
	</c:choose>
</body>
</html>