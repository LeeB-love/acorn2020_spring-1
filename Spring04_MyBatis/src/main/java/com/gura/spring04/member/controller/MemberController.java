package com.gura.spring04.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dto.MemberDto;
import com.gura.spring04.member.service.MemberService;

@Controller
public class MemberController {
	//의존객체를 주입받는다.
	@Autowired //의존객체가 여러개인 경우에는 id를 부여해서 뭘 가져올지 아이디 명시하기
	private MemberService service;
	
	//회원추가 폼 요청 처리
	@RequestMapping("/member/insertform")
	public String insertform() {
		//수행할 비즈니스 로직은 현재 없다.
		return "member/insertform";
	}
	
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		
		service.getListMember(mView);
		
		//view 페이지 정보를 담고
		mView.setViewName("member/list");
		return mView;
	}
	
	@RequestMapping("/member/insert")
	public String insert(@ModelAttribute MemberDto dto) {
		service.addMember(dto);
		//view page로 forward 이동해서 응답
		return "member/insert";
	}
	
	@RequestMapping("/member/delete")
	public String delete(@RequestParam int num) {
		service.deleteMember(num);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value = "/member/updateform", method = RequestMethod.GET)
	//GET 방식/member/updateform 요청 처리 (경로는 value, 전송방식은 method) 이렇게 명칭하면 post 방식일 땐 응답하지 않는다.
	public ModelAndView updateform(@RequestParam int num, ModelAndView mView) {
		service.getMember(num, mView);
		mView.setViewName("member/updateform");
		
		return mView;
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String update(@ModelAttribute MemberDto dto) {
		service.updateMember(dto);
		return "member/update";
	}
	
	
}
/*
* controller 에서는 로직처리를 간단하게 해야함. 어떻게 처리하고 어디로 이동할 것인지만 처리하고 비즈니스 로직은  service에서 처리한다. 
*/





