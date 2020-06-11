package com.sist.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	private MongoTemplate mt;
	
	public List<BoardVO> boardListData(int page)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		Query query=new Query(); // String sql="SELECT~~ WHERE no=1" find({no:1})
		int rowSize=10;
		int skip=(rowSize*page)-rowSize;
		query.skip(skip).limit(rowSize); // 페이지 나누기
		query.with(new Sort(Sort.Direction.DESC,"no")); // 정렬
		// ORDER BY no DESC
		list=mt.find(query, BoardVO.class,"board"); // board는 테이블명 List<?> ?에 BoardVO.class가 들어감
		// list=selectList(); ==> NoSQL
		return list;
	}
	
	public int boardTotalPage()
	{
		int total=0;
		Query query=new Query(); // find({}) 조건이 없는 경우
		int count=(int)mt.count(query, "board"); // SELECT COUNT(*) FROM board; 리턴형이 long
		total=(int)(Math.ceil(count/10.0));
		return total;
	}
	public void boardInsert(BoardVO vo)
	{
		Query query=new Query();
		query.with(new Sort(Sort.Direction.DESC,"no")); // ORDER BY 
		BoardVO rvo=mt.findOne(query, BoardVO.class,"board");
		vo.setNo(rvo.getNo()+1);
		vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // SYSDATE
		vo.setHit(0);
		
		mt.insert(vo,"board");
	}
}
