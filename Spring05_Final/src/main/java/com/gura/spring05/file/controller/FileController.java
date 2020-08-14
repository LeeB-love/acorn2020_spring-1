package com.gura.spring05.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.file.dto.FileDto;
import com.gura.spring05.file.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	
	/*
	* request객체에 setAttribute해서 데이터들을 view 페이지에 넘겨줘야하므로 HttpServletRequest 객체 생성
	* ModelAndView 객체에 응답경로 쌓아서 forward 이동
	*/
	@RequestMapping("/file/list")
	public ModelAndView list(HttpServletRequest request, ModelAndView mView) {
		//fileService를 이용해서 비즈니스 로직 처리하고
		fileService.getList(request);
		//view 페이지로 forward 이동해서 응답하기
		mView.setViewName("file/list");
		return mView;
	}
	
	@RequestMapping("/file/private/upload_form")
	public ModelAndView uploadForm(ModelAndView mView) {
		mView.setViewName("file/private/upload_form");
		return mView;
	}
	
	@RequestMapping(value = "/file/private/upload", method = RequestMethod.POST)
	public ModelAndView upload(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		fileService.saveFile(dto, mView, request);
		mView.setViewName("file/private/upload");
		return mView;
	}
	
	@RequestMapping("/file/download")
	public ModelAndView download(@RequestParam int num, ModelAndView mView) {
		//mView에 다운로드 할 파일의 정보를 담고
		fileService.getFileData(num, mView);
		//view 페이지로 이동해서 다운로드 시켜준다.
		//@Component("fileDownView") 가 붙어있는 AbstractView 객체를 찾아간다.
		mView.setViewName("file/download");
		return null;
	}
	
	@RequestMapping("/file/private/delete")
	public ModelAndView delete(@RequestParam int num, ModelAndView mView, HttpServletRequest request) {
		fileService.deleteFile(num, request);
		mView.setViewName("redirect:/file/list.do");
		return mView;
	}
}
/*
* setViewName을 하면 안의 이름을 일단 0순위로 spring container안의 view 중에서 찾는다.
* 근데 없을 땐  그 다음으로 /views/file/download.jsp로 가서 찾음
* 1의 view는 jsp페이지들이 모여있는 곳이 아니라 file.view 패키지의 java 파일임.
*/