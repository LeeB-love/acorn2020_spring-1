<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test10.jsp</title>
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
</head>
<body ng-app>
	<select ng-model="selectedFruit" ng-change="selectedFruit2=selectedFruit">
		<option value="">선택</option>
		<option value="orange">오렌지</option>
		<option value="apple">사과</option>
		<option value="banana">바나나</option>
	</select>
	<p>선택된 과일 : <strong ng-bind="selectedFruit" ng-model="selectedFruit2" ></strong></p>
		<select ng-model="selectedFruit2">
		<option value="">선택</option>
		<option value="orange">오렌지</option>
		<option value="apple">사과</option>
		<option value="banana">바나나</option>
	</select>
	<h2>체크박스를 테스트해보세요</h2>
	<label>
		<input type="checkbox" ng-model="isShow"/> isShow
	</label>
	<label>
		<input type="checkbox" ng-model="isMake" />isMake
	</label>
	<!-- isShow는 p요소를 만들고 css로 숨겼다 나타냈다 하는 것 -->
	<p ng-show="isShow">안녕하세요</p>
	<!-- isMake는 p요소 자체를 동적으로 만들어내는것 -->
	<p ng-if="isMake">또 만났군요~</p>
</body>
</html>