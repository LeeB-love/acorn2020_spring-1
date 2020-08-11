<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
   <h1>인덱스 페이지입니다.</h1>
   <ul>
      <li><a href="member/list.do">회원 목록 보기</a></li>
      <li><a href="todo/list.do">할 일 목록 보기</a></li>
      <li><a href="json01.do">json 응답1</a></li>
      <li><a href="json02.do">json 응답2</a></li>
      <li><a href="json03.do">json 응답3</a></li>
      <li><a href="json04.do">json 응답4</a></li>
      <li><a href="json05.do">json 응답5</a></li>
      <li><a href="json06.do">json 응답6</a></li>
      <li><a href="json07.do">json 응답7</a></li>
      <li><a href="json08.do">json 응답8</a></li>
      <li><a href="xml01.do">xml 응답01</a></li>
      <li><a href="xml02.do">xml 응답02</a></li>
      <li><a href="xml03.do">xml 응답03</a></li>
   </ul>
   <button id="testBtn">ajax 테스트</button>
   
   <h2>파일 업로드 테스트</h2>
   <form action="upload.do" method="post" enctype="multipart/form-data">
	   	제목<input type="text" name="title" /><br />
	   	첨부파일<input type="file" name="myFile" /><br />
	   	<button type="submit">업로드</button>
   </form>
   
   <h2>파일 업로드 테스트2</h2>
   <form action="upload2.do" method="post" enctype="multipart/form-data">
	   	제목<input type="text" name="title" /><br />
	   	첨부파일<input type="file" name="myFile" /><br />
	   	<button type="submit">업로드</button>
   </form>
</div>
<script src="resources/js/jquery-3.5.1.js"></script>
<script>
   $("#testBtn").on("click", function(){
      $.ajax({
         url:"json02.do",
         method:"GET",
         //서버가 클라이언트에게 응답한 내용을 jquery가 받아 data로 전달한다.
         success:function(data){
            //data는 plain object or array이다.
            console.log(data);
         }
      });
   });
</script>
</body>
</html>
