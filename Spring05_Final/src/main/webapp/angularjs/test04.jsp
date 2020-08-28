<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test04.jsp</title>
<!-- bootstrap css 로딩 시키기 -->
<link href="../resources/css/bootstrap.css" rel="stylesheet"/>
<!-- angularjs 로딩 시키기 -->
<script src="../resources/js/angular.min.js"></script>
</head>
<!-- body 에서 일어나는 일은 angular 로 관리를 하겠다라는 의미 -->
<body ng-app>
<div class="container">
	<h1>form 검증</h1>
	<form name="myForm" action="insert.jsp" method="post" novalidate>
		<!-- 입력한 문자열을 id 라는 모델명으로 관리, 반드시 입력해야 한다. -->
		<input type="text" name="id" ng-model="id" ng-required="true"/>
		<p ng-show="myForm.id.$invalid && myForm.id.$dirty">ㅇㅅㅇ</p>
		<button type="submit" ng-disabled="myForm.id.$invalid">제출</button>	
	</form>
	<p> 입력한 아이디 : <strong>{{id}}</strong></p>
	<p> 아이디 유효한지 여부 : <strong>{{myForm.id.$valid}}</strong></p>
	<p> 아이디 유효하지 않은지 여부 : <strong>{{myForm.id.$invalid}}</strong></p>
	<p> 아이디가 청결한지 여부 : <strong>{{myForm.id.$pristine}}</strong></p> <!-- input 요소에 한번도 뭔가가 입력되지 않으면 true -->
	<p> 아이디가 더렵혀졌는지 여부 : <strong>{{myForm.id.$dirty}}</strong></p> <!-- input 요소에 한번이라도 뭔가가 입력된적이 있으면 true -->
</div>
</body>
</html>