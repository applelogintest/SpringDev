package com.sist.dao;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.mongodb.*;

@Repository
public class MovieDAO {
	private MongoClient mc; // Connection
	private DB db; // DataBase (XE)
	private DBCollection dbc; // Table
	
	public MovieDAO()
	{
		try{
			mc=new MongoClient("localhost",27017);
			db=mc.getDB("mydb");
			dbc=db.getCollection("movie");
			
		}catch (Exception ex) {}
	}
	//{mno:100,title:'',....} JSON형식 ==> BasicDBObject
	public void movieInsert(MovieVO vo){
		try{
			BasicDBObject obj=new BasicDBObject();
			obj.put("mno", vo.getMno());
			obj.put("title", vo.getTitle());
			obj.put("poster",vo.getPoster());
			obj.put("director", vo.getDirector());
			obj.put("actor", vo.getActor());
			obj.put("genre", vo.getGenre());
			obj.put("grade", vo.getGrade());
			obj.put("story", vo.getStory());
			
			dbc.insert(obj);
		}catch (Exception ex) {}
	}
	
	public List<MovieVO> movieListData(int page)
	{
		List<MovieVO> list=new ArrayList<MovieVO>();
		try{
			int rowSize=12;
			int skip=(rowSize*page)-rowSize;
			DBCursor cursor=dbc.find().skip(skip).limit(rowSize);// DBCusor jdbc의 ResultSet
			while(cursor.hasNext())
			{
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MovieVO vo=new MovieVO();
				vo.setMno(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				
				list.add(vo);
			}
			cursor.close();
		}catch (Exception ex) {}
		return list;
	}
	
	//CURD
	public List<MovieVO> movieFindData(String fd)
	{
		// SELECT * FROM movie WHERE title LIKE '%fd%'
		// find({"title",{"$regex",".*"+fd}})
		List<MovieVO> list=new ArrayList<MovieVO>();
		try{
			BasicDBObject where=
					new BasicDBObject("title",new BasicDBObject("$regex",".*"+fd));
			DBCursor cursor=dbc.find(where);
			
			while(cursor.hasNext())
			{
				BasicDBObject obj=(BasicDBObject)cursor.next();
				// {}
				MovieVO vo=new MovieVO();
				vo.setMno(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				
				list.add(vo);
						
			}
			cursor.close();
			
		}catch (Exception ex) {}
		return list;
	}
	
	public MovieVO movieDetailData(int mno)
	{
		MovieVO vo=new MovieVO();
		try{
			BasicDBObject where=new BasicDBObject("mno",mno);
			// WHERE mno=10
			BasicDBObject res=(BasicDBObject)dbc.findOne(where);
			vo.setMno(res.getInt("mno"));
			vo.setTitle(res.getString("title"));
			vo.setActor(res.getString("actor"));
			vo.setDirector(res.getString("director"));
			vo.setPoster(res.getString("poster"));
			vo.setGenre(res.getString("genre"));
			vo.setGrade(res.getString("grade"));
			vo.setStory(res.getString("story"));
			
		}catch (Exception ex) {}
		return vo;
	}
	
	
}
