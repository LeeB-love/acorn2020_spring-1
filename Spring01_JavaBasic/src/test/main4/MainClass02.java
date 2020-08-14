package test.main4;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass02 {
	public static void main(String[] args) {
		//회원 가입할 때 폼에 입력했던 비밀번호라고 가정
		String pwd="abcd1234";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String savedPwd = encoder.encode(pwd);
		
		System.out.println("비밀번호 입력 : ");
		//로그인 폼에 입력한 비밀번호라고 가정
		String inputPwd = new Scanner(System.in).nextLine();
		
		//DB에 저장된 비밀번호와 입력한 비밀번호가 일치하는지 여부.. 엥? 절대 불가능하지
		//boolean isEqual = inputPwd.equals(savedPwd);
		
		//암호화된 비밀번호를 일치하는지 비교해주는 static method
		boolean isEqual = BCrypt.checkpw(inputPwd, savedPwd);
		if(isEqual) {
			System.out.println("비밀번호가 일치합니다.");
		}else {
			System.out.println("비밀번호가 틀립니다.");
		}
	}
}
