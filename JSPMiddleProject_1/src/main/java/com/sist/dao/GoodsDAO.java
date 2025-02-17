package com.sist.dao;

import java.util.*; 
import com.sist.vo.*;
import java.io.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//2.xx => iBatis 3.xx => MyBatis
// open source     googled에서 인수

public class GoodsDAO {
	private static SqlSessionFactory ssf;
	//관리자 => 연결 => sql문장 처리 => XML파일을 파싱 요청
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
	public static List<GoodsVO> goodsListData(Map map)
	{
		return ssf.openSession().selectList("goodsListData",map);
	}
	public static int goodsTotalPage()
	{
		return ssf.openSession().selectOne("goodsTotalPage");
	}
	
}
