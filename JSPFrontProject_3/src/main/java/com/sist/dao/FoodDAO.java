package com.sist.dao;

import com.sist.vo.*; 
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static
	{
		/*
		 * XML을 읽어서 필요한 데이터 추출 => 파싱
		 * HTML : Jsoup
		 * XML : DocumentBuilder
		 * JSON : json-simple.jackson
		 * 
		 * Mybatis / Spring / Spring-boot => 자동으로 파싱
		 */
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<FoodVO> foodFindData(Map map)
	{
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData",map);
		session.close();
		
		return list;
	}
	
	public static int foodFindTotalPage(String address)
	{
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodFindTotalPage",address);
		session.close();
		return total;
	}
	
	public static FoodVO foodFindDetailData(int fno)
	{
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodFindDetailData",fno);
		session.close();
		return vo;
	}
	
}
