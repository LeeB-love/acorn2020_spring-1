package com.gura.spring05.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class TimeAspect { //실행하는데 걸리는 시간 구하기

	@Around("execution(* com.gura.spring05.file.controller.*.*(..))")
	public Object checkTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//로그를 출력할 객체의 참조값 얻어오기
		//joinPoint.getTarget().getClass() => aop가 적용된 클래스의 type
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.info("요청처리 시작!");
		
		//시작 시간(ms)
		long startTime = System.currentTimeMillis();
		
		/*
		 * joinPoint.proceed()가 리턴하는 것 : joinPoint인 메소드가 리턴하는 것을 리턴한다.
		 * ex) 	@RequestMapping("/file/list")
				public ModelAndView list(HttpServletRequest request, ModelAndView mView) {
					//fileService를 이용해서 비즈니스 로직 처리하고
					fileService.getList(request);
					//view 페이지로 forward 이동해서 응답하기
					mView.setViewName("file/list");
					return mView;
				}
				의 경우 mView가 jointPoint.proceed()의 리턴타입.
		*/
		Object obj = joinPoint.proceed();
		
		//끝 시간(ms)
		long endTime = System.currentTimeMillis();
		//시작과 끝의 차이를 얻어낸다.
		long time = endTime-startTime;
		logger.info("time : "+time);
		logger.info("요청 처리 끝!");
		
		ModelAndView mView = null;
		if(obj instanceof ModelAndView) {
			mView=(ModelAndView)obj;
			mView.setViewName("redirect:/home.do");
		}
		
		return obj;
	}
}
