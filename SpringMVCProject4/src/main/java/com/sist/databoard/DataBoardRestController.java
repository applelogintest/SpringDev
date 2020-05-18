package com.sist.databoard;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.*;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("board/")
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@RequestMapping("update_ok.do")
	public String databoard_update_ok(DataBoardVO vo)
	{
		String result="";
		// 비밀번호 체크
		String db_pwd=dao.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			result="OK";
			dao.databoardUpdate(vo);
		}
		else
		{
			result="NOPWD";
		}
		return result;
	}
	
	@RequestMapping("delete_ok.do")
	public String databoard_delete_ok(int no,String pwd)
	{
		String result="";
		DataBoardVO vo=dao.databoardFileInfoData(no);
		boolean bCheck=dao.databoardDelete(no, pwd);
		if(bCheck==true)
		{
			result="OK";
			try{
				if(vo.getFilecount()>0)
				{
					StringTokenizer st=new StringTokenizer(vo.getFilename(),",");
					while(st.hasMoreTokens())
					{
						File file=new File("c:\\upload\\"+st.nextToken());
						file.delete();
					}
				}
				File file=new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SpringMVCProject4\\board\\"+no+".png");
				file.delete();
			}catch (Exception ex) {}
		}
		else
		{
			result="NOPWD";
		}
		return result;
	}
}
