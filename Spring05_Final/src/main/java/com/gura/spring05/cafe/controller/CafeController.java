package com.gura.spring05.cafe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeCommentDto;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService service;
	
	
	//카페 글 목록보기 요청처리
	@RequestMapping("/cafe/list")
	public ModelAndView getList(HttpServletRequest request, ModelAndView mView) {
		service.getList(request);
		mView.setViewName("cafe/list");
		return mView;
	}
	
	
	//상세글 보기 페이지 설정
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(HttpServletRequest request, ModelAndView mView) {
		service.getDetail(request);
		mView.setViewName("cafe/detail");
		return mView;
	}
	
	
	//새 글 작성
	@RequestMapping("/cafe/private/insertform")
	public ModelAndView insertForm(ModelAndView mView) {
		mView.setViewName("cafe/private/insertform");
		return mView;
	}
	
	@RequestMapping(value = "/cafe/private/insert", method=RequestMethod.POST)
	public ModelAndView insert(CafeDto dto, ModelAndView mView, HttpSession session) {
		//dto 에 글 작성자 담기 
		String id=(String)session.getAttribute("id");
		dto.setWriter(id);
		service.saveContent(dto);
		mView.setViewName("cafe/private/insert");
		return mView;
	}
	
	//글 삭제하기
	@RequestMapping("/cafe/private/delete")
	public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
		service.deleteContent(num, request);
		mView.setViewName("cafe/private/delete");
		return mView;
	}
	
	//글 수정하기
	@RequestMapping("/cafe/private/updateform")
	public ModelAndView updateForm(ModelAndView mView, HttpServletRequest request) {
		service.getDetail(request);
		mView.setViewName("cafe/private/updateform");
		return mView;
	}
	
	@RequestMapping("/cafe/private/update")
	public ModelAndView update(ModelAndView mView, CafeDto dto) {
		service.updateContent(dto);
		mView.setViewName("cafe/private/update");
		return mView;
	}
	
	@RequestMapping(value ="/cafe/private/comment_insert", method = RequestMethod.POST)
	public ModelAndView commentInsert(HttpServletRequest request, 
			ModelAndView mView,@RequestParam int ref_group) {
		//새 댓글을 저장하고
		service.saveComment(request);
		//보고있던 글 자세히 보기로 다시 리다이렉트 이동 시킨다.
		mView.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return mView;
	}
	
	@RequestMapping("/cafe/private/comment_delete")
	public ModelAndView commentDelete(HttpServletRequest request, ModelAndView mView, @RequestParam int ref_group) {
		service.deleteComment(request);
		mView.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return mView;
	}
	
	/*
	*ajax 요청에 map으로 응답 키값은 String type, value는 object 타입의 map
	*객체를 만들어 ajax 요청에 응답한다.
	*/
	@RequestMapping(value="/cafe/private/comment_update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commentUpdate(CafeCommentDto dto){
		//댓글을 수정 반영하고
		service.updateComment(dto);
		//JSON 문자열을 클라이언트에게 응답한다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		return map;
	}
	
	
	@RequestMapping("/cafe/ajax_comment_list")
	public ModelAndView ajaxCommentList(HttpServletRequest request,
			ModelAndView mView) {
		service.moreCommentList(request);
		mView.setViewName("cafe/ajax_comment_list");
		return mView;
	}
	

	
}
















