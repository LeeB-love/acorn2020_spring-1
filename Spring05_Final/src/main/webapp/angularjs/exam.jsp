<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
<script>
	var acornApp = angular.module("acornApp",[]);
	acornApp.controller("acornCtrl", function($scope){
		$scope.btnClick=function(){
			alert("버튼을 눌렀네요?");
			$scope.msg="버튼을 눌렀네요?";
		};
	})
</script>
</head>
<body ng-app="acornApp">
	<div ng-controller="acornCtrl">
		<button ng-click="btnClick()">눌러보시오</button>
		<p ng-bind="msg"></p>
	</div>
</body>
</html>


