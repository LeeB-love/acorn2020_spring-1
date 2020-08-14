package com.gura.spring05.file.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.exception.NotDeleteExeption;
import com.gura.spring05.file.dao.FileDao;
import com.gura.spring05.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService{
  
	@Autowired
    private FileDao fileDao;
   
   
	//한 페이지에 나타낼 row 의 갯수
   final int PAGE_ROW_COUNT=5;
   //하단 디스플레이 페이지 갯수
   final int PAGE_DISPLAY_COUNT=5;
   
   
   @Override
   public void getList(HttpServletRequest request) {
      
      //로그인 된 아이디 읽어오기 (로그인을 하지 않으면 null 이다)
      String id=(String)request.getSession().getAttribute("id");
      
      //보여줄 페이지의 번호
      int pageNum=1;
      //보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.   
      String strPageNum=request.getParameter("pageNum");
      if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
         //페이지 번호를 설정한다.
         pageNum=Integer.parseInt(strPageNum);
      }
      //보여줄 페이지 데이터의 시작 ResultSet row 번호
      int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
      //보여줄 페이지 데이터의 끝 ResultSet row 번호
      int endRowNum=pageNum*PAGE_ROW_COUNT;
      /*
         검색 키워드에 관련된 처리 
      */
      String keyword=request.getParameter("keyword"); //검색 키워드
      String condition=request.getParameter("condition"); //검색 조건
      if(keyword==null){//전달된 키워드가 없다면 
         keyword=""; //빈 문자열을 넣어준다. 
         condition="";
      }
      //인코딩된 키워드를 미리 만들어 둔다. 
      String encodedK=URLEncoder.encode(keyword);
      
      //검색 키워드와 startRowNum, endRowNum 을 담을 FileDto 객체 생성
      FileDto dto=new FileDto();
      dto.setStartRowNum(startRowNum);
      dto.setEndRowNum(endRowNum);
      
      if(!keyword.equals("")){ //만일 키워드가 넘어온다면 
         if(condition.equals("title_filename")){
            //검색 키워드를 FileDto 객체의 필드에 담는다. 
            dto.setTitle(keyword);
            dto.setOrgFileName(keyword);   
         }else if(condition.equals("title")){
            dto.setTitle(keyword);
         }else if(condition.equals("writer")){
            dto.setWriter(keyword);
         }
      }
      //파일 목록 얻어오기
      List<FileDto> list=fileDao.getList(dto);
      //전체 row 의 갯수 
      int totalRow=fileDao.getCount(dto);
      
      //전체 페이지의 갯수 구하기
      int totalPageCount=
            (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
      //시작 페이지 번호
      int startPageNum=
         1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
      //끝 페이지 번호
      int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
      //끝 페이지 번호가 잘못된 값이라면 
      if(totalPageCount < endPageNum){
         endPageNum=totalPageCount; //보정해준다. 
      }
      
      //EL 에서 사용할 값을 미리 request 에 담아두기
      request.setAttribute("list", list);
      request.setAttribute("startPageNum", startPageNum);
      request.setAttribute("endPageNum", endPageNum);
      request.setAttribute("pageNum", pageNum);
      request.setAttribute("totalPageCount", totalPageCount);
      request.setAttribute("condition", condition);
      request.setAttribute("keyword", keyword);
      request.setAttribute("encodedK", encodedK);      
   }

   
   
	@Override
	public void saveFile(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		
		//업로드된 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값 얻어오기
		MultipartFile myFile = dto.getMyFile();
		
		//원본 파일명
		String orgFileName = myFile.getOriginalFilename();
		//파일의 크기
		long fileSize = myFile.getSize();
		
		//지역변수에 HttpServletRequest 객체를 선언해서 받는다.
		//webapp/upload폴더까지의 실제경로를 출력한다. (request의 getServletContext 메소드의 getRealPath 메소드 사용)
		String realPath = request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세경로
		String filePath = realPath+File.separator; //File.separator : \ 운영체제에 따라서 역슬래시(윈도우)나 슬래시(리눅스)
		
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(filePath);
		if(!upload.exists()) { //만일 upload라는 디렉토리가 존재하지않으면 만들어준다.(upload 폴더 만들기)
			upload.mkdir();
		}
		
		//저장할 파일명을 구성하세요~ (currentTimeMill을 원본파일 이름에 붙여서 파일명이 겹치지 않도록 saveFileName을 구성한다.)
		String saveFileName = System.currentTimeMillis()+orgFileName;
		
		try {
			//upload 폴더에 파일을 저장한다. (transferTo가 upload폴더로 파일을 옮겨준다.)
			myFile.transferTo(new File(filePath+saveFileName)); 
			//myFile에 올라온 파일을 지정경로(filePath:어쩌구저쩌구/upload)에  저장할 이름(saveFileName)으로 저장됨
			System.out.println(filePath+saveFileName); //저장된 파일 경로와 저장된 이름을 console에 출력
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//dto에 업로드된 위에서 알아낸 파일의 정보를 담는다.
		String id = (String)request.getSession().getAttribute("id");
		dto.setWriter(id);
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
	
		//fileDao를 이용해서 DB 에 저장하기
		fileDao.insert(dto);
		//view 페이지에서 사용할 모델 담기
		mView.addObject("dto", dto);
	}

	
	
	@Override
	public void getFileData(int num, ModelAndView mView) {
		//fileDao를 이용해서 파일 정보를 얻어온 다음
		FileDto dto = fileDao.getData(num);
		//mView 객체에 담는다.
		mView.addObject("dto", dto);
		
	}



	@Override
	public void deleteFile(int num, HttpServletRequest request) {
		//1. 사제할 파일의 정보를 읽어온다.
		FileDto dto = fileDao.getData(num);
		//2. 본인이 작성한 글이 아닌 경우 에러처리를 한다.(예외를 발생시킨다)
		String id=(String)request.getSession().getAttribute("id");
		//만일 로그인 된 아이디와 글 작성자가 다르면
		if(!id.equals(dto.getWriter())) {
			throw new NotDeleteExeption("남의 파일을 지우지 마시오");
		}
		//파일 시스템에서 파일 삭제
		String saveFileName = dto.getSaveFileName();
		String path = request.getServletContext().getRealPath("/upload")+File.pathSeparator+saveFileName;
		new File(path).delete();
		//DB에서 파일 정보 삭제
		fileDao.delete(num);
	}
   
}
