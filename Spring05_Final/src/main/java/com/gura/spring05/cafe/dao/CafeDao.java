package com.gura.spring05.cafe.dao;

import java.util.List;

import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeDao {

	//글목록
	public List<CafeDto> getList(CafeDto dto);
	
	//글의 개수
	public int getCount(CafeDto dto);
	
	//글 추가
	public void insert(CafeDto dto);
	
	//글정보 얻어오기
	public CafeDto getData(int num);
	
	//키워드를 활용한 글정보 얻어오기
	public CafeDto getData(CafeDto dto); //메소드 오버로딩(다중정의) 같은 메소드에 다른 인자를 넣어 쓸 수있다.
	
	//조회수 증가시키기
	public void addViewCount(int num);
	
	//글 삭제
	public void delete(int num);
	
	//글 수정
	public void update(CafeDto dto);
	
}
