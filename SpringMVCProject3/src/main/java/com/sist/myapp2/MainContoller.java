package com.sist.myapp2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//[{키:값,키:값}]  [] 배열

@Controller
@RequestMapping("main/")
public class MainContoller {
	//http://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do
	@RequestMapping("main.do")
	public String maind_main(Model model)
	{
		/*if(no==null)
			no="1";
		
		String url="";
		switch(Integer.parseInt(no)){
		//일일 박스오피스
		case 1:
			url="searchMainDailyBoxOffice.do";
			break;
			//실시간 예매율
		case 2:
			url="searchMainRealTicket.do";
			break;
			//좌석점유율순위
		case 3:
			url="searchMainDailySeatTicket.do";
			break;
			//온라인상영관 일일
		case 4:
			url="searchMainOnlineDailyBoxOffice.do";
			break;
		}*/
		model.addAttribute("main_jsp","default.jsp");
		return "main/main";
	}
}
