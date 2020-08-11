package com.gura.spring04.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;
/*
* 비즈니스 로직을 처리할 서비스 클래스 정의하기
*/
//bean이 되도록 @Service 어노테이션을 붙인다.
@Service
public class MemberServiceImpl implements MemberService{

	//의존 객체 주입받기
	@Autowired
	private MemberDao dao;
	
	@Override
	public void addMember(MemberDto dto) {
		dao.insert(dto);
		
	}

	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
		
	}

	@Override
	public void deleteMember(int num) {
		dao.delete(num);
		
	}

	@Override
	public void getMember(int num, ModelAndView mView) {
		MemberDto dto = dao.getData(num);
		mView.addObject("dto", dto);
		
	}

	@Override
	public void getListMember(ModelAndView mView) {
		List<MemberDto> list = dao.getList();
		mView.addObject("list", list);
	}

}
