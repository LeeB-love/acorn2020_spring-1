package com.gura.spring05.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class UsersController {

		@Autowired
		private UsersService service;
		
		//회원가입 폼 요청 처리
		@RequestMapping("/users/signup_form")
		public String signupForm() {
			
			// /WEB-INF/views/usesrs/signup_form.jsp 페이지로 forward 이동해서 응답
			return "users/signup_form"; //요청 경로와 view 페이지는 상관이 없지만, 너무 상관없으면 코딩하기 힘드니까 비슷하거나 같게 쓰는 것
		}
		
		
		//아이디가 존재하는지 여부를 처리하는 요청처리
		/*
		* {"isExist":true} 나  {"isExist":false}를 응답받기 위해 Map을 리턴타입으로 지정
		* service에서 map을 리턴받아 ㄱㄱ
		*/
		//아이디가 존재하는지 여부를 처리하는 요청처리
		@RequestMapping("/users/checkid")
		@ResponseBody
		public Map<String, Object> checkid(@RequestParam String inputId){
			//service  가 리턴해주는 Map 객체를 리턴한다.
			return service.isExistId(inputId);
		}
		
		@RequestMapping("/users/signup")
		public ModelAndView signup(UsersDto dto, ModelAndView mView) {
			//service 객체를 이용해서 사용자 정보를 추가한다.
			service.addUser(dto);
			//view 페이지로 forward 이동해서 응답하기
			mView.setViewName("users/signup");
			return mView;	
		}
		
		@RequestMapping("/users/loginform")
		public String loginform(HttpServletRequest request) {
			//url 파라미터가 넘어오는지 읽어와보기
			String url = request.getParameter("url");
			if(url==null){//만약 목적지 정보가 없다면
				String cPath = request.getContextPath();
				url=cPath+"/home.do"; // "/"하나만 써도 index.jsp 페이지로 넘어가긴 하지만, 명시적으로 index.jsp 써주기 
			}
			request.setAttribute("url", url);
			return "users/loginform";
		}
		
		@RequestMapping("/users/login")
		public ModelAndView login(UsersDto dto, ModelAndView mView, HttpSession session, HttpServletRequest request) {
			//로그인 후 가야하는 목적지 정보
			String url=request.getParameter("url");
			String encodedUrl=URLEncoder.encode(url);
			mView.addObject("url", url);
			mView.addObject("encodedUrl", encodedUrl);
			
			service.loginProcess(dto, mView, session);
			mView.setViewName("users/login");
			return mView;
		}
		
		@RequestMapping("/users/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/home.do";
		}
		
		@RequestMapping("/users/private/delete")
		public ModelAndView delete(HttpServletRequest request,
				ModelAndView mView) {
			//서비스를 이용해서 사용자 정보를 삭제하고
			service.deleteUser(request.getSession());
			//인덱스 페이지로 리다이렉트 이동
			mView.setViewName("users/private/delete");
			return mView;
		}
		
	   //개인정보보기 요청 처리
	   @RequestMapping("/users/private/info")
	   public ModelAndView info(HttpServletRequest request, ModelAndView mView) {
	      service.getInfo(request.getSession(), mView);
	      mView.setViewName("users/private/info");
	      return mView;
	   }
	   
	   //회원정보 수정 폼 요청처리
	   @RequestMapping("/users/private/updateform")
	   public ModelAndView updateForm(HttpServletRequest request, ModelAndView mView) {
		   service.getInfo(request.getSession(), mView);
		   mView.setViewName("users/private/updateform");
		   return mView;
	   }
	   
	   //ajax 프로필 사진 업로드 요청 처리 (잠깐! servlet-context.xml에 multipartResolver가 있는지 확인하기)
	   //service는 image를 받아서 map을 리턴해주면 됨 
		@RequestMapping("/users/private/profile_upload")
		@ResponseBody
		public Map<String, Object> profile_upload
					(HttpServletRequest request,@RequestParam MultipartFile image){
			//service 객체를 이용해서 이미지를 upload 폴더에 저장하고 Map 을 리턴 받는다.
			Map<String, Object> map=service.saveProfileImage(request, image);
			//{"imageSrc":"/upload/xxx.jpg"} 형식의 JSON 문자열을 출력하기 위해
			//Map 을 @ResponseBody 로 리턴해준다. 
			return map;
		}
	   
	   //개인 정보 수정 반영 요청 처리
	   @RequestMapping("/users/private/update")
	   public ModelAndView update(HttpServletRequest request, UsersDto dto, ModelAndView mView) {
		  
		  service.updateUser(request.getSession(), dto);
		   
		   mView.setViewName("redirect:/users/private/info.do");
		   return mView;
		   
	   }
	   
	   //비밀번호 변경 요청 처리
	   @RequestMapping("/users/private/pwd_updateform")
	   public ModelAndView pwdUpdateform(ModelAndView mView) {
		   mView.setViewName("users/private/pwd_updateform");
		   return mView;
	   }
	   
	   @RequestMapping("/users/private/pwd_update")
	   public ModelAndView  pwdUpdate(ModelAndView mView, UsersDto dto, HttpServletRequest request) {
		   //service 객체를 이용해서 새로운 비밀번호로 수정한다.
		   service.updateUserPwd(request.getSession(), dto, mView);
		   //view 페이지로 forward 이동해서 응답
		   mView.setViewName("users/private/pwd_update");
		   return mView;
	   }
		
}














