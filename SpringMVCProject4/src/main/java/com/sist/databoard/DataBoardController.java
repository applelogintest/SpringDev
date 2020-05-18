package com.sist.databoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.manager.RManager;

@Controller
@RequestMapping("board/")
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	@Autowired
	private RManager rm;
	
	@RequestMapping("list.do")
	public String board_list(Model model, String page)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<DataBoardVO> list=dao.databoardListData(map);
		int totalpage=dao.databoardTotalPage();
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		
		return "board/list";
	}
	
	@RequestMapping("insert.do")
	public String board_insert()
	{
		return "board/insert";
	}
	
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(DataBoardVO vo) throws Exception
	{
		System.out.println(vo.getName());
		List<MultipartFile> list=vo.getFiles();
		String temp1="";
		String temp2="";
		if(list!=null && list.size()>0)
		{
			for(MultipartFile mf:list)
			{
				String fn=mf.getOriginalFilename(); // 사용자가 보내준 파일의 이름
				File file=new File("c:\\upload\\"+fn);
				mf.transferTo(file);
				
				temp1+=fn+",";
				temp2+=file.length()+",";
			}
			temp1=temp1.substring(0,temp1.lastIndexOf(","));
			temp2=temp2.substring(0,temp2.lastIndexOf(","));
			
			vo.setFilecount(list.size());
			vo.setFilename(temp1);
			vo.setFilesize(temp2);
		}
		else
		{
			vo.setFilecount(0);
			vo.setFilename("");
			vo.setFilesize("");
		}
		
		dao.databoardInsert(vo);
		return "redirect:list.do";
	}
	
	@RequestMapping("detail.do")
	public String board_detail(Model model,int no)
	{
		DataBoardVO vo=dao.databoardDetail(no);
		if(vo.getFilecount()>0)
		{
			StringTokenizer st1=new StringTokenizer(vo.getFilename(),",");
			List<String> fList=new ArrayList<String>();
			while(st1.hasMoreTokens())
			{
				fList.add(st1.nextToken());
			}
			
			StringTokenizer st2=new StringTokenizer(vo.getFilesize(),",");
			List<String> sList=new ArrayList<String>();
			while(st2.hasMoreTokens())
			{
				sList.add(st2.nextToken());
			}
			model.addAttribute("fList",fList);
			model.addAttribute("sList",sList);
		}
		
		
		model.addAttribute("vo",vo);
		
		// 그래프 출력
		try{
			FileWriter fw=new FileWriter("c:\\data\\board.txt");
			fw.write(vo.getContent()+"\r\n");
			fw.close();
			
			rm.rGraph(no);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "board/detail";
	}
	
	@RequestMapping("download.do")
	public void databoard_download(String fn,HttpServletResponse response)
	{
		try{
			File file=new File("c:\\upload\\"+fn);
			response.setHeader("Content-Disposition", "attachhment;filenames="
					+ URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// 서버 => c:\\upload\\a.jpg
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); // 클라이언트의 저장장소에 저장
			// 클라이언트 영역
			
			int i=0;
			byte[] buffer=new byte[1024];
			
			while((i=bis.read(buffer,0,1024))!=-1)
			{
				bos.write(buffer,0,i);
			}
			
			bis.close();
			bos.close();
		}catch (Exception ex) {}
	}
	
	@RequestMapping("update.do")
	public String databoard_update(Model model,int no)
	{
		DataBoardVO vo=dao.databoardUpdate(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	/*
	 	JSP ==> DispatcherServlet ==> @Controller
	 								  ============ @RequestMapping ===> 결과값출력(JSP)
	 								  							   DAO
	 								  							Model(request)
	 	 	link : delete.do
	 */
	@RequestMapping("delete.do")
	public String databoard_delete(Model model,int no)
	{
		model.addAttribute("no",no);
		return "board/delete";
	}
	
	
}
