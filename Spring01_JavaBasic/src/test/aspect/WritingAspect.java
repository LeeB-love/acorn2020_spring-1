package test.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * Aspect : 공통 관심사 (횡단 관심사)
 * 
 * -핵심 비즈니스 로직과는 상관없는 공통 관심사를 따로 작성한다.
 * 따로 작성해서 갖다 붙일 수 있음
 */


/*
 * 	-Aspect Expression
 * 
 * 	※ return type 표기법
 * 	(..) : 있어도되고 없어도 되고
 * 	() : 없어야함
 *  (*) : 반드시 1개
 *  (*, *, *,..., *) : 반드시 n개
 * 
 * 	1. execution(* *(..)) => 접근 가능한 모든 메소드가 point cut (aop가 적용되는 지점을 point cut 이라고 한다.)
 * 	2. execution(* test.service.*.*(..)) 
 * 		=> test.service 패키지의 모든 메소드 point cut
 * 	3. execution(void insert*(..))
 * 		=>리턴 type 은 void 이고 메소드명이 
 * 		insert 로 시작하는 모든 메소드가 point cut
 * 	4. execution(* delete*(*))
 * 		=> 메소드 명이 delete 로 시작하고 인자로 1개 전달받는 
 *      메소드가 point cut (aop 가 적용되는 위치)
 * 	5. execution(* delete*(*,*))
 * 		=> 메소드 명이 delete 로 시작하고 인자로 2개 전달받는 
 *      메소드가 point cut (aop 가 적용되는 위치)
 *  6. execution(String update*(Integer, *))
 *  	=> 메소드 명이 update로 시작하고 리턴 type은 String 
 *  	   메소드의 첫번째 인자는 Integer type, 두 번째 인자는 아무 type다되는 메소드가 point cut(aop 가 적용되는 위치)
 */

@Aspect
@Component //component scan을 통해서 bean이 되기 위한 어노테이션
public class WritingAspect {
	//write로 시작하는 모든 메소드
	@Before("execution(void write*())")
	public void prepare() {
		System.out.println("pen을 준비해요");
	}
	
	@After("execution(void write*())")
	public void end() {
		System.out.println("다 사용한 pen 을 마무리해요. ");
	}
}
