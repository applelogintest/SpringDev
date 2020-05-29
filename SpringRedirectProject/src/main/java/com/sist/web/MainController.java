package com.sist.web;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*
 	웹 ===> 주고 받기(서버 => 처리) => 결과값 => 받기(출력)
 	C(Front)/S(Back) (client/server) ==> 데이터
 	== 데이터 관리 프로그램 (CURD)
 */
@Controller
public class MainController {
	@RequestMapping("main/list.do")
	public String main_list(Model model)
	{
		model.addAttribute("msg", "Redirect를 이용시 데이터 전송");
		return "main/list";
	}
	
	@RequestMapping("main/insert.do")
	public String main_insert()
	{
		return "redirect:result.do?id=admin";
	}
	
	@RequestMapping("main/update.do")
	public String main_update(RedirectAttributes r)
	{
		r.addFlashAttribute("id","admin"); // result => model.addAttribute()
		return "redirect:result.do";
	}
	
	@RequestMapping("main/result.do")
	public String main_result(String id,Model model)
	{
		//model.addAttribute("id", id);
		return "main/result";
	}
}
