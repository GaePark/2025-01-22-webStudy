package com.sist.dao;
import com.sist.vo.*;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
public class BoardDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	public static List<BoardVO> boardListData(Map map)
	{
		SqlSession session = null;
		List<BoardVO> list = null;
		try {
			session=ssf.openSession();
			list=session.selectList("boardListData",map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(session!=null) session.close();
		}
		return list;
	}
	public static int boardTotalPage()
	{
		SqlSession session = ssf.openSession();
		int total = session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	public static BoardVO boardDetailData(int no)
	{
		SqlSession session= null;
		BoardVO vo = null;
		try {
			
			session = ssf.openSession();
			session.update("boardHitIncrement",no);
			session.commit();
			vo = session.selectOne("boardDetailData",no);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			session.close();
			
		}
		return vo;
	}
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert",vo);
		session.close();
	}
/*
 * <!-- 수정 -->
<update id="boardUpdate" parameterType="BoardVO" >
UPDATE project_board SET
name=#{name},subject=#{subject},content=#{content}
WHERE no=#{no}
</update>
<!-- 삭제 -->
<select id="boardGetPassword" resultType="String" parameterType="int" >
	SELECT pwd FROM project_board
	WHERE no=#{no}
</select>
<delete id="boardDelete" parameterType="int">
	DELETE FROM project_board
	WHERE no=#{no}
</delete>
 */
	public static BoardVO boardUpdateData(int no)
	{
		SqlSession session= null;
		BoardVO vo = null;
		try {
			
			session = ssf.openSession();
			vo = session.selectOne("boardUpdateData",no);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			session.close();
			
		}
		return vo;
	}
	public static void boardUpdate(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);
		session.update("boardUpdate",vo);
		session.close();
	}
	public static String boardGetPassword(int no)
	{
		SqlSession session = ssf.openSession();
		String pwd=session.selectOne("boardGetPassword",no);
		session.close();
		return pwd;
	}
	public static void boardDelete(int no)
	{
		SqlSession session=ssf.openSession(true);
		session.delete("boardDelete",no);
		session.close();
	}
}

