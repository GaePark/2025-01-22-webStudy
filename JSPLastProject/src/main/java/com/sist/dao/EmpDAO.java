package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	public static List<EmpVO> empGetEnameData()
	{
		SqlSession session = null;
		List<EmpVO> list= null;
		try {
			session=ssf.openSession();
			list= session.selectList("empGetEnameData");
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(session!=null) session.close();
		}
		return list;
	}
	
	public static List<EmpVO> empFindData(Map map)
	{
		SqlSession session = null;
		List<EmpVO> list= null;
		try {
			session=ssf.openSession();
			list= session.selectList("empFindData",map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(session!=null) session.close();
		}
		return list;
	}
}
