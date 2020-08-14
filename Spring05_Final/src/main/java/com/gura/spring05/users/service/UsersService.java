package com.gura.spring05.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersService {
	
	//String을 전달받아서 Map을 리턴받음
	public Map<String, Object> isExistId(String inputId);
	
	public void addUser(UsersDto dto);
	
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session);
	
	public void deleteUser(HttpSession session);
	
	//session으로 개인정보를 불러온다.
	public void getInfo(HttpSession session, ModelAndView mView);
	
	public Map<String, Object> saveProfileImage(HttpServletRequest request,
			MultipartFile mFile);
	
	public void updateUser(HttpSession session, UsersDto dto);
	
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView mView);
}
