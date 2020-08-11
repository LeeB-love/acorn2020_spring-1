<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/insertform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<h1>수정폼입니다.</h1>
	<form action="update.do" method="post">
		<input type="hidden" name="num" value="${dto.num }" />
		<div class="form-group">
			<input type="text" id="num" class="form-control" value=${dto.num } disabled />
		</div>
		<div class="form-group">
			<input class="form-control" type="text" name="name" value="${dto.name }" />	
		</div>
		<div class="form-group">
			<input class="form-control" type="text" name="addr" value="${dto.addr }"/>
		</div>
		<button type="submit" class="btn btn-primary">수정</button>
	</form>
</div>
</body>
</html>