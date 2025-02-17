package com.sist.dao;

import java.util.*; 

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;


public class FoodDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static FoodVO foodDetailData(int fno)
	{
		ssf.openSession(true).update("hitIncrement",fno);
		//조회수 증가가 안된다
		return ssf.openSession().selectOne("foodDetailData",fno);
	}
	
	public static List<FoodVO>foodListData(Map map)
	{
		return ssf.openSession().selectList("foodListData",map);
	}
	
	public static int foodTotalPage()
	{
		return ssf.openSession().selectOne("foodTotalPage");
	}
	
	
}
