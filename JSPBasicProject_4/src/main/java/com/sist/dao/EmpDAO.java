package com.sist.dao;
/*
 * 
 */

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class EmpDAO {
	PreparedStatement ps;
	Connection conn;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static EmpDAO dao;
	
	public EmpDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static EmpDAO newInstance()
	{
		if(dao==null)
			dao=new EmpDAO();
		return dao;
	}
	
	public void getConnection()
	{
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void disConnection()
	{
		try {
			if(conn!=null) conn.close();
			if(ps != null) ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//기능
	public List<EmpVO> empListData()
	{
		List<EmpVO> list=  new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql = "SELECT empno,ename,job,hiredate,sal "
					+ "FROM emp ORDER BY empno ASC";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			EmpVO vo = new EmpVO();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setHiredate(rs.getDate(4));
			vo.setSal(rs.getInt(5));
			list.add(vo);
		}
		rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	
}
