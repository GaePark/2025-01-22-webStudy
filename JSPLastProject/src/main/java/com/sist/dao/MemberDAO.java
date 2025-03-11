package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;
public class MemberDAO {
private static SqlSessionFactory ssf;

/*
 * 동적쿼리
 * 프로시저 / 트리거
 * 							ㅣlike / reply
 *   ㅣall_reply , all_like,all_jjim
 * ==================================
 * 2중 include
 * 
 */

static {
	ssf=CreateSqlSessionFactory.getSsf();
}

public static int memberIdcheck(String id)
{
	SqlSession session = null;
	int count = 0;
	try {
		session=ssf.openSession();
		count=session.selectOne("memberIdcheck",id);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally
	{
		if(session!=null) session.close();
	}
	return count;
}
public static void memberInsert(MemberVO vo)
{
	SqlSession session = null;
	try {
		session=ssf.openSession(true);
		session.insert("memberInsert",vo);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally
	{
		if(session!=null) session.close();
	}
}
public static MemberVO memberLogin(String id, String pwd)
{
	SqlSession session =null;
	MemberVO vo = new MemberVO();
	try {
		session=ssf.openSession();
		int count=session.selectOne("memberIdCheckCount",id);
		if(count==0)
		{
			vo.setMsg("NOID");
		}
		else {
			vo=session.selectOne("memberGetPassword",id);
			if(pwd.equals(vo.getPwd()))
			{
					vo.setMsg("OK");
			}
			else
			{
				vo.setMsg("NOPWD");
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		if(session!=null) session.close();
	}
	return vo;
}
}
