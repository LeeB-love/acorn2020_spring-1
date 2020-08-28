<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/angularjs/test01.jsp</title>
<!-- angularjs 로딩시키기 -->
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
</head>
<body ng-app>
	<h1>hello angular js!</h1>
	<!-- input에 입력한 문자열을 msg 안에 담겠다는 의미 -->
	<input ng-model="msg" type="text" placeholder="메세지 입력..." />
	<!-- msg를 출력하겠소 ==> msg가 바뀌면 바로 반영이 된다. -->
	<p>{{msg}}</p>
	<h1 ng-init="friends=['김구라', '해골', '원숭이']">친구 목록 입니다.</h1>
	<ul>
		<li ng-repeat="tmp in friends">{{tmp}}</li>
	</ul>
	<h1>체크박스를 체크해보세요</h1>
	<input type="checkbox" ng-model="isShow" /><strong>{{isShow}}</strong><br />
	<img src="../resources/images/kim1.png" ng-show="isShow" /> <!-- ui를 조건부로 보이게 할것인지 말것인지를 결정 -->
</body>
</html>
<!-- 웹브라우저가 해석하는 자바 스크립트로, jsp나 서버는 관여하지 않는다. 
모델은 뷰에, 뷰는 모델에 양방향으로 연결되어 있기 때문에 모델의 변경 사항이 바로바로 뷰에 반영이 된다.-->