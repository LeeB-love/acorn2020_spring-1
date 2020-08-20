<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%-- 
	ajax는 자바스크립트를 이용하여
	주소창 변경 없이(새로고침 혹은 다른 곳으로 이동하는 등 화면 전체가 처음부터 다시 로딩)
	사용자의 요청에 서버가 응답하게 하는 것.
	ajax 응답은 문자열 응답만 가능하다. 파일 응답 안됨.
	
	- ajaxForm 플러그인
	form 플러그인은 url, method, data, success 적을 필요 없이 .ajaxForm(function(){});
	이라고 쓰면 알아서 ajax로 실행된다. (subnit 버튼을 눌렀을 때 ajax 요청이 감)
	
	-ajaxSubmit 플러그인
	submit 플러그인은 form이 실행될 때 ajax로 제출 (페이지 로딩되는 시점에 강제 제출됨)
	
	<댓글 페이징 응답 과정>
	1. 사용자가 스크롤을 내린다. 그러면 자동적으로 scoll 이벤트가 일어났을 때 실행되는 함수가 실행된다.
	2. 만약 바닥까지 스크롤을 내리면 페이지 수를 1 증가시키고 다음 페이지의 내용을 ajax를 통해 요청한다.
	3. controller 에서 ajax_comment_list.do에서 요청이 왔다는 것을 감지하고 요청에 대한 비즈니스 로직을 service에서 처리한다.
	4. controller가 응답할 로직과 응답할 페이지를 ModelAndView 객체에 담아 return한다.
	5. ajax가 cafe/ajax_comment_list.jsp에서 응답된 문자열을 data에 담아 success:function()에 인자로 넣어준다.
	6. 받은 data로 datail.jsp 페이지에서 클라이언트에게 응답한다.
 --%>

<c:forEach var="tmp" items="${commentList }">
	<c:choose>
		<c:when test="${tmp.deleted eq 'yes' }">
			<li>삭제된 댓글 입니다.</li>
		</c:when>
		<c:otherwise>
			<li id="comment${tmp.num }" <c:if test="${tmp.num ne tmp.comment_group }">style="padding-left:50px;"</c:if>>
				<c:if test="${tmp.num ne tmp.comment_group }"><svg class="reply-icon" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-return-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
 						<path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
 						<path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z"/></svg>
				</c:if>
				<dl>
					<dt>
						<c:choose>
							<c:when test="${empty tmp.profile }">
								<svg class="profile-image"  width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
		  							<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
								</svg>
							</c:when>
							<c:otherwise>
								<img class="profile-image" 
									src="${pageContext.request.contextPath }${tmp.profile }"/>
							</c:otherwise>
						</c:choose>
						<span>${tmp.writer }</span>
						<c:if test="${tmp.num ne tmp.comment_group }">
							@<i>${tmp.target_id }</i>
						</c:if>
						<span>${tmp.regdate }</span>
						<a data-num="${tmp.num }" href="javascript:" class="reply-link">답글</a>
						<c:if test="${tmp.writer eq id }">
							| <a data-num="${tmp.num }" href="javascript:" class="comment-update-link">수정</a>
							| <a data-num="${tmp.num }" href="javascript:" class="comment-delete-link">삭제</a>
						</c:if>
					</dt>
					<dd>
						<pre>${tmp.content }</pre>
					</dd>
				</dl>
				<form class="comment-form re-insert-form" 
					action="private/comment_insert.do" method="post">
					<input type="hidden" name="ref_group"
						value="${dto.num }"/>
					<input type="hidden" name="target_id"
						value="${tmp.writer }"/>
					<input type="hidden" name="comment_group"
						value="${tmp.comment_group }"/>
					<textarea name="content"></textarea>
					<button type="submit">등록</button>
				</form>
				<!-- 로그인된 아이디와 댓글의 작성자가 같으면 수정 폼 출력 -->
				<c:if test="${tmp.writer eq id }">
					<form class="comment-form update-form" 
						action="private/comment_update.do" method="post">
						<input type="hidden" name="num" value="${tmp.num }"/>
						<textarea name="content">${tmp.content }</textarea>
						<button type="submit">수정</button>
					</form>
				</c:if>
			</li>						
		</c:otherwise>
	</c:choose>
</c:forEach>