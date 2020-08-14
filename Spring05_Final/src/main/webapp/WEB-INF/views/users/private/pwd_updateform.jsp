<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/pwd_updateform.do</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<h1>비밀번호 수정폼</h1>
	<form action="pwd_update.do" method="post" id="myForm">
		<div class="form-group">
			<label for="pwd">기존 비밀번호</label>
			<input class="form-control" type="text" name="pwd" id="pwd" value = "${dto.pwd }"/>
		</div>
		<div class="form-group">
			<label for="newPwd">새 비밀번호</label>
			<input class="form-control" type="password" name="newPwd" id="newPwd"/>
		</div>
		<div class="form-group">
			<label for="newPwd2">새 비밀번호 확인</label>
			<input class="form-control" type="password" name="newPwd2" id="newPwd2"/>
		</div>
		<button class="btn btn-primary" type="submit">수정하기</button>
	</form>
</div>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<script>
	//id 가 myForm 인 곳에 submit 이벤트가 일어났을 때 실행할 함수 등록
	$("#myForm").on("submit",function(){
		//입력한 새로운 비밀번호 2개를 읽어와서
		var pwd1=$("#newPwd").val();
		var pwd2=$("#newPwd2").val();
		//만일 일치하지 않으면
		if(pwd1 != pwd2){
			//알림을 띄우고
			alert("새 비밀번호가 일치하지 않습니다.")
			//비밀번호 입력란을 초기화하고 포커스 주기
			$("#newPwd").val("").focus();
			$("#newPwd2").val("");
			//폼전송을 막는다.
			return false;
		}
	});
</script>
</body>
</html>