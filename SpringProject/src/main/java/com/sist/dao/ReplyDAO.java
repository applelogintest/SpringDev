package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// EMP(DAO) Dept(DAO) => EmpDept (Service)
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public void replyInsert(ReplyVO vo)
	{
		mapper.replyInsert(vo);
	}
	
	public List<ReplyVO> replyListData(int mno)
	{
		return mapper.replyListData(mno);
	}
	/*
	 	try{
	 		getConnection();
	 		conn.setAutoCommit(false);
	 		==============================
	 		ReplyVO pvo=mapper.replyParentInfoData(pno);
			mapper.replyGroupStepIncreament(pvo);
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(pvo.getRoot());
			mapper.replyInsert(vo);
			mapper.replyDepthIncreament(pno);
			
			conn.commit();
			============== Around
	 	}catch(Exception ex)
	 	{
	 		conn.rollback(); AfterThrowing
	 		System.out.println(ex.getMessage());   ===> rollbackFor=Exception.class
	 	}
	 	finally
	 	{
	 		conn.setAutoCommit(true); After
	 	}
	 */
	
	// Propagation.REQUIRED 반드시 처리 / rollbackFor=Exception.class 롤백시 에러메세지 출력
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void replyReplyInsert(int pno,ReplyVO vo)
	{
		ReplyVO pvo=mapper.replyParentInfoData(pno);
		mapper.replyGroupStepIncreament(pvo);
		System.out.println("pno :"+pno);
		System.out.println("id :"+pvo.getGroup_id());
		System.out.println("step :"+pvo.getGroup_step());
		System.out.println("tab :"+pvo.getGroup_tab());
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pvo.getRoot());
		
		mapper.replyIReplyInsert(vo);
		mapper.replyDepthIncreament(pno);
	}
	
	public void replyUpdate(ReplyVO vo)
	{
		mapper.replyUpdate(vo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void replyDelete(int no)
	{
		ReplyVO vo=mapper.replyInfoData(no); // depth, root
		if(vo.getDepth()==0)
		{
			mapper.replyDelete(no);
		}
		else
		{
			ReplyVO rvo=new ReplyVO();
			rvo.setNo(no);
			rvo.setMsg("관리자가 삭제한 댓글입니다.");
			mapper.replyMsgUpdate(rvo);
		}
		mapper.replyDepthDecrement(vo.getRoot());
	}
}
