package test.exam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Phone;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("test/exam/init.xml");
		Phone phone =context.getBean(Phone.class);
		phone.callToMom();
		phone.callToFather();
	
	}
}
