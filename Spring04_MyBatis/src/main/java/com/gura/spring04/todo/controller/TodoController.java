package com.gura.spring04.todo.controller;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.todo.dto.TodoDto;
import com.gura.spring04.todo.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService service;
	
	@RequestMapping("/todo/list")
	public ModelAndView list(ModelAndView mView) {
		service.getListTodo(mView);
		mView.setViewName("todo/list");
		return mView;
	}
	
	@RequestMapping("/todo/delete")
	public String delete(@RequestParam int num) {
		service.deleteTodo(num);
		return "redirect:/todo/list.do";
	}
	
	@RequestMapping("/todo/insert")
	public String insert(@ModelAttribute TodoDto dto) {
		service.addTodo(dto);
		return "redirect:/todo/list.do";
	}
	
	@RequestMapping("/todo/updateform")
	public ModelAndView updateform(@RequestParam int num, ModelAndView mView) {
		service.getTodo(num, mView);
		mView.setViewName("todo/updateform");
		return mView;
	}
	
	@RequestMapping(value = "/todo/update", method = RequestMethod.POST)
	public String update(@ModelAttribute TodoDto dto) {
		service.updateTodo(dto);
		return "todo/list";
	}
}
