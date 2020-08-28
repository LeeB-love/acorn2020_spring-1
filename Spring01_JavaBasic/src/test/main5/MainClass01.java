package test.main5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.WritingUtil;

public class MainClass01 {
	public static void main(String[] args) {
		
		/*
		* 1. ClassPathXmlApplicationContext 객체를 생성하면 test/main/init.xml 이 실행된다.
		* 2. init.xml 에 있는 component-scan이 진행되고 @Component 붙어있던 WritingUtil 객체가 Spring Bean으로 생성되어 관리된다.
		* 3. 생성된 Bean들은 ApplicationContext 객체 안에 저장이 된다.
		* 4. getBean() 메소드를 통해 Bean 객체를 불러올 수 있다.
		*/
		
		//init.xml 문서를 로딩해서 bean으로 관리할 객체들을 관리한다.
		ApplicationContext context = new ClassPathXmlApplicationContext("test/main5/init.xml");
		
		//관리되는 객체 중에서 WritingUtil type 객체의 참조값을 얻어온다.
		WritingUtil util = context.getBean(WritingUtil.class);
		//객체의 메소드 호출하기
		util.write1();
		util.write2();
		util.write3();
	}
}
