package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;
public class BoardDAO {
	private static SqlSessionFactory ssf;
	// XML의 데이터를 저장 => SqlSessionFactory
	//insert/update/delete/select => 관리 => SqlSession
	// SqlSession은 SqlSessionFactory가 생성
	// SqlSession session = ssf.openSession()
	// connection / preparedstatement => close() 반환
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception e) {
			e.printStackTrace();
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
		int total = session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	public static void insertBoard(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);
		session.insert("insertBoard", vo);
		session.close();
	}
}
