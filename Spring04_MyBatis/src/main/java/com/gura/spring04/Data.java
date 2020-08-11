package com.gura.spring04;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
*rootElement를 어떻게 쓰냐에 따라 응답이 달라짐
*/
@XmlRootElement(name="data")
public class Data {
	private int num;
	private String name;
	public int getNum() {
		return num;
	}
	@XmlElement
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
}
