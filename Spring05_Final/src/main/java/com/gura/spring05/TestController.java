package com.gura.spring05;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class TestController {

	@Autowired
	UsersService usersService;
	
	@RequestMapping("/api/get_info")
	@ResponseBody
	public JSONPObject jsonp(@RequestParam String callback) {
		//클라이언트에게 응답할 데이터를 Map에 담는다.
		Map<String, Object> map= new HashMap<>();
		map.put("num", 1);
		map.put("name","김구라");
		//JSONPObject 객체를 생성해서 콜백함수명과 응답할 데이터를 담고 
		JSONPObject jp =new JSONPObject(callback, map);
		//JSONPObject를 리턴해준다.
		return jp;
	}
	
	@RequestMapping("/api/jsonp_login")
	@ResponseBody
	public JSONPObject jsonpLogin(String callback, UsersDto dto) {
		boolean isValid=usersService.jsonpLogin(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("isValid", isValid);
		
		JSONPObject jp = new JSONPObject(callback, map);
		return jp;
	}
}
