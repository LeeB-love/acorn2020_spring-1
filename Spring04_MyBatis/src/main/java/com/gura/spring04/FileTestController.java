package com.gura.spring04;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileTestController {

	/*
	* <input type="file" name="myFile"/> 하나만 전송되는 경우 MultipartFile myFile이라고 해도 됨
	* Spring은 파일이 업로드되면 업로드 된걸 임시폴더에 저장해놓고 그것에 대한 정보를 MultipartFile객체에
	* 담아서 전달해준다.
	* 원본파일명, 임시폴더에 저장되어있는 파일을 옮겨서 저장할 폴더를 만들고 이름이 겹치지 않게 저장해서 쌓는다.
	*/
	
	@RequestMapping("/upload")
	public String upload(@RequestParam MultipartFile myFile, 
			HttpServletRequest request, @RequestParam String title) {
		
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
		
		request.setAttribute("orgFileName", orgFileName);
		request.setAttribute("saveFileName", saveFileName);
		request.setAttribute("fileSize", fileSize);
		request.setAttribute("title", title);
		
		return "upload";
	}
		
		
		@RequestMapping("/upload2")
		public String upload2(HttpServletRequest request, FileDto dto) {
			
			MultipartFile myFile=dto.getMyFile();
			
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
			
			//업로드된 파일정보를 FileDto에 담는다.
			dto.setOrgFileName(orgFileName);
			dto.setSaveFileName(saveFileName);
			dto.setFileSize(fileSize);
			
			//request 영역에 FileDto 담기
			request.setAttribute("dto", dto);
			
			// /WEB-INF/views/upload2.jsp 페이지로 forward 이동해서 응답
			return "upload";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
