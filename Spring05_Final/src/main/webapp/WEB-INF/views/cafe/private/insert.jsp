<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/private/insert.jsp</title>
</head>
<body>
<script>
	alert("${id}님이 작성하신 글을 저장했습니다.");
	location.href="${pageContext.request.contextPath }/cafe/list.do"
</script>
</body>
</html>





