<%@page import="javax.sound.midi.SysexMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// callback 이라는 파라미터 명으로 전달되는 문자열 읽어오기
	String callback=request.getParameter("callback");
	// 클라이언트에게 응답할 데이터라고 가정
	int num=1;
	String name="김구라";
	System.out.println("callback 함수명 : "+callback);
%>
<%
//만약 파라미터 없이 그냥 요청하면 json형식의 문자열 응답, callback이라는 파라미터 명으로 전달되는 문자열이
//있으면 callback(); 메서드 안에 오브젝트 형식으로 자바스크립트 전달
if(callback != null){ %>
	<%=callback %>({num:<%=num %>, name:"<%=name %>"});
<%}else{ %>
	{"num":<%=num %>, "name":"<%=name %>"}
<%} %>
