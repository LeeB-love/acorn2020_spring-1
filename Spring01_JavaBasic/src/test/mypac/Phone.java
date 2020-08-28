package test.mypac;

import org.springframework.stereotype.Component;

@Component
public class Phone {
	public void callToMom() {
		System.out.println("엄마에게 전화를 걸어요");
	}
	
	public void callToFather() {
		System.out.println("아빠에게 전화를 걸어요");
	}
}
