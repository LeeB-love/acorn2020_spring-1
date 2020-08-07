package com.gura.spring04.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring04.member.dto.MemberDto;

@Repository //dao를 bean 으로 만들기 위한 어노테이션(spring이 관리하는 객체를 만들기 위한 어노테이션)
public class MemberDaoImpl implements MemberDao{
	//의존 객체 주입받기(Dependency Injection)
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(MemberDto dto) {
		/*
		* mapper namespace : member
		* sql id : insert
		* parameterType : MemberDto
		*/
		session.insert("member.insert",dto);
		
	}

	@Override
	public void update(MemberDto dto) {
		/*
		* mapper namespace : member
		* sql id= update
		* parameterType : MemberDto
		*/
		session.update("member.update",dto);
		
	}

	@Override
	public void delete(int num) {
		/*
		* mapper namespace : member
		* sql id : delete
		* parameterType : int or java.lang.Integer
		*/
		session.delete("member.delete", num);
		
	}

	@Override
	public MemberDto getData(int num) {
		/*
		* mapper namespace : member
		* sql id : getData
		* parameterType : int
		* resultType : MemberDto
		* -resultTupe은 select 된 row 하나를 담을 데이터 type을 의미한다.
		*/
		MemberDto dto = session.selectOne("member.getData", num);
		return dto;
	}

	@Override
	public List<MemberDto> getList() {
		/*
		* resultType : MemberDto
		* -resultTupe은 select 된 row 하나를 담을 데이터 type을 의미한다.
		* -selectList() 메소드는 List type을 리턴하고 List의 generic type이 resultType이 된다.
		*/
		List<MemberDto> list = session.selectList("member.getList");
		return list;
	}
	/*
	* select 할 때 하나만 셀렉할지, 여러줄을 셀렉할지 생각해보고 selectOne, selectList 둘 중에 골라서 사용
	*/
}






