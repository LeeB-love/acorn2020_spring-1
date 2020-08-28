package test.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PhoneAspect {
	@Before("execution(* *(..))")
	public void prepare() {
		System.out.println("전화기를 준비해요");
	}
}
