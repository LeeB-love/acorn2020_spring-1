package com.gura.spring04;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
* XmlRootElement 의 이름으로 지정된 hobby-list 요소안에 
* hobby라는 이름의List<String>에 add되어있던 요소들이 출력되는 형태
* 
* List는 controller에서 사용하기
*/

@XmlRootElement(name="hobby-list")
public class HobbyList {
	
	private List<String> hobby;
	
	@XmlElement
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	
	public List<String> getHobby() {
		return hobby;
	}
}
