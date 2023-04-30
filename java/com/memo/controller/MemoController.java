package com.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemoController {
	
	@RequestMapping(value="/memo", method=RequestMethod.GET)
	public String memoForm() {
		
		return "memo/input";//뷰네임
		// /WEB-INF/views/memo/input.jsp
	}
	
	@RequestMapping(value="/memo", method=RequestMethod.POST)
	public String memoInsert(Model model) {
	
		model.addAttribute("msg", "한줄 메모장 등록 테스트중입니다");
		model.addAttribute("loc","memoList");
		
		return "message";
		///WEB-INF/views/message.jsp
	}
	
	@RequestMapping("/memoList")
	public String memoList(Model model) {
		
		return "memo/list";
	}

	@RequestMapping(value="/memoEdit", method=RequestMethod.GET)
	public String memoEditForm() {
		
		return "memo/edit";
	}
	
	@RequestMapping(value="/memoEdit", method=RequestMethod.POST)
	public String memoEditEnd(Model model) {
		
		model.addAttribute("msg","수정 테스트");
		model.addAttribute("loc","memoList");
		return "message";
	}
	
	@RequestMapping("/memoDel")
	public String memoDelete(Model model) {
		
		model.addAttribute("msg","삭제 테스트");
		model.addAttribute("loc","memoList");
		return "message";
	}
	

}








