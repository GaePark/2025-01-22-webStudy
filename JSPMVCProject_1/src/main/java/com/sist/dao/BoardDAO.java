package com.sist.dao;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	
	static {
		try {
			//1. Config파일 읽기
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//2. session제작
			ssf= new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static List<BoardVO> boardListData(Map map)
	{
		SqlSession session=ssf.openSession();
		List<BoardVO> list = session.selectList("boardListData", map);
		session.close();
		return list;
	}
	public static int boardTotalPage()
	{
		SqlSession session=ssf.openSession();
		int totalpage = session.selectOne("boardTotalPage");
		session.close();
		return totalpage;
	}
	public static BoardVO boardDetailData(int no)
	{
		SqlSession session = ssf.openSession();
		session.update("hitIncrement",no);
		session.commit();
		BoardVO vo = session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	public static String boardGetPassword(int no)
	{
		SqlSession session = ssf.openSession();
		String pwd = session.selectOne("boardGetPassword",no);
		session.close();
		return pwd;
	}
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert",vo);
		session.close();
	}
	public static void boardDelete(int no)
	{
		SqlSession session = ssf.openSession();
		session.delete("boardDelete",no);
		session.commit();
		session.close();
	}
	public static void boardUpdate(BoardVO vo)
	{
		SqlSession session= ssf.openSession();
		session.update("boardUpdate",vo);
		session.commit();
		session.close();
	}
	
}
