package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static FoodVO foodMainHouseData()
	{
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodMainHouseData");
		session.close();
		return vo;
	}
	public static List<FoodVO> foodMainHouseData8()
	{
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodMainHouseData8");
		session.close();
		return list;
	}
	public static List<FoodVO> foodListData(Map map)
	{
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData",map);
		session.close();
		return list;
	}
	public static int foodTotalPage()
	{
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
}
