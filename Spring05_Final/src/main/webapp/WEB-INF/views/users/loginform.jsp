<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
	 <div class="container">
	 	<h1>로그인</h1>
	 	<form action="login.do" method="post">
	 	<%--원래 가려던 목적지 정보를 url 이라는 파라미터명으로 가지고 간다. --%>
	 		<input type="hidden" name="url" value="${url }" />
	 		<div class="form-group">
	 			<label for="id">아이디</label>
	 			<input class="from-control" type="text" name="id" id="id" />
	 		</div>
	 		<div class="form-group">
	 			<label for="pwd">비밀번호</label>
	 			<input class="from-control" type="text" name="pwd" id="pwd" />
	 		</div>
	 		<button class="btn btn-primary" type="submit">로그인</button>
	 	</form>
	 </div>
</body>
</html>