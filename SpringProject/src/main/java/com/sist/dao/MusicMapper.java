package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 	public void insert() {}
 	public void update() {}
 	
 	@Transactional
 	public void replyInsert(){
 		try{
 			ssf.openSession()
 			입고)insert(); 정상 
 			재고)update(); 오류 
 			재고)insert(); 정상 
 			commit(); ==> Around
 			
 		}catch(Exception ex)
 		{
 			rollback(); => After-Throwing
 		}
 		finally
 		{
 			conn.setAutoCommit(); => After
 		}
 	}
 */
public interface MusicMapper {
	@Select("SELECT * FROM music_genie ORDER BY mno ASC")
	public List<MusicVO> musicListData();
	
	@Select("SELECT * FROM music_genie "
			+ "WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno);
	
	@Select("SELECT COUNT(*) FROM member "
			+ "WHERE id=#{id}")
	public int idCount(String id);
	
	@Select("SELECT pwd FROM member "
			+ "WHERE id=#{id}")
	public String memberGetPassword(String id);
	
	
}
