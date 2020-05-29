package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.mongodb.util.JSON;
import com.sist.dao.*;
// {} => ROW
// [{},{},{},{}..] [] => JSONArray {} => JSONObject
@RestController
public class MusicController {
	@Autowired
	private MusicDAO dao;
	
	@RequestMapping("main/music.do")
	public String main_list()
	{
		String result="";
		List<MusicVO> list=dao.musicListData();
		JSONArray arr=new JSONArray();
		for(MusicVO vo:list)
		{
			JSONObject obj=new JSONObject();
			obj.put("mno", vo.getMno());
			obj.put("title", vo.getTitle());
			obj.put("singer", vo.getSinger());
			obj.put("album",vo.getAlbum());
			obj.put("state", vo.getState());
			obj.put("idcrement", vo.getIdcrement());
			obj.put("poster", vo.getPoster());
			arr.add(obj);
		}
		
		result=arr.toJSONString();
		
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("main/detail_data.do")
	public String detail_data(int mno)
	{
		MusicVO vo=dao.musicDetailData(mno);
		JSONObject obj=new JSONObject();
		obj.put("mno", vo.getMno());
		obj.put("title", vo.getTitle());
		obj.put("singer", vo.getSinger());
		obj.put("album", vo.getAlbum());
		obj.put("state", vo.getState());
		obj.put("idcrement", vo.getIdcrement());
		obj.put("poster", vo.getPoster());
		obj.put("key", vo.getKey());
		
		return obj.toJSONString();
	}
	
	
}
