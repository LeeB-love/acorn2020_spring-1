package test.main4;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass01 {
	public static void main(String[] args) {
		//비밀번호라고 가정
		String pwd="abcd1234";
		//CharSequece는 String의 부모타입. encode 메소드는 CharSequence를 요구하는데 String이 자식 타입이라 String으로도 사용 가능
		CharSequence pwd2="abcd1234";
		//비밀번호를 인코딩할 객체 생성
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for(int i=0; i<10; i++) {
			//비밀번호를 암호화한 문자열 얻어내기
			String result = encoder.encode(pwd);
			//결과 출력해보기
			System.out.println(result);
		}
	}
}
