<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/private/upload_form.do</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<h1>파일 업로드 폼입니다.</h1>
	<%--
		[파일 업로드 폼 작성법]
		1. method="post"
		2. enctype="multipart/form-data" 속성추가
		3. <input type="file" />을 이용한다.
		4. 파일을 업로드 할 폴더를 미리 만들어준다.
		5. upload처리하기 위해서 필요한 라이브러리 : commons jar파일 두 개  
		6. upload.do 설정 
	 --%>
	<form action="upload.do" method="post" enctype="multipart/form-data"> <%--file 타입이 있는 경우엔 폼 전송방식이 좀 다르다. enctype을 추가해주어야함. --%>
		<div class="form-group">
			<%--그리고 lib 폴더에 commons파일 두 개 붙여넣기 --%>
			<label for="title">제목(설명)</label>
			<input class="form-control" type="text" name="title" id="title" /><br />
		</div>
		<div class="form-group">
			<label for="myFile">첨부파일</label>
			<input class="form-control" type="file" name="myFile" id="myFile" />
		</div>
		<button class="btn btn-primary" type="submit">업로드</button>
	</form>
</div>
</body>
</html>