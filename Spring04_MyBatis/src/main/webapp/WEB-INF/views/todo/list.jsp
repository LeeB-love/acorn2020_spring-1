<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>할 일 목록</h1>
	<form action="insert.do">
		<input type="text" name="work" id="work"/>
		<button type="submit">할 일 추가</button>
	</form>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>할 일</th>
				<th>작성일</th>
				<th>삭제</th>
				<th>수정</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tmp" items="${list }">
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.work }</td>
					<td>${tmp.regdate }</td>
					<td><a href="delete.do?num=${tmp.num } ">삭제</a></td>
					<td><a href="updateform.do?num=${tmp.num }">수정</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>