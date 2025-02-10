package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.ReplyVO;

public class ReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static ReplyDAO dao;
	
	public ReplyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static ReplyDAO newInstance()
	{
		if(dao==null)
			dao= new ReplyDAO();
		return dao;
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void disConnection()
	{
		try {
			if(conn!=null) conn.close();
			if(ps!=null) ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//기능
	/*
RNO	NUMBER
FNO	NUMBER
ID	VARCHAR2(20 BYTE)
NAME	VARCHAR2(51 BYTE)
MSG	CLOB
REGDATE	DATE
	 */
	public void replyInsert(ReplyVO vo)
	{
		try {
			getConnection();
			String sql = "INSERT INTO mreply "
					+ "VALUES(mreply_rno_seq.nextval,?,?,?,?,SYSDATE)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getMno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	
	public List<ReplyVO> replyListData(int mno){
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql = "SELECT rno,mno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
					+ "FROM mreply "
					+ "WHERE mno="+mno
					+ "ORDER BY rno DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				ReplyVO vo = new ReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setMno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setMsg(rs.getString(5));
				vo.setDbday(rs.getString(6));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return list;
	}
	
	public void replyDelete(int rno)
	{
		try {
			getConnection();
			String sql = "DELETE mreply WHERE rno="+rno;
			ps= conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	
	public void replyUpdate(int rno, String msg)
	{
		try {
			getConnection();
			String sql = "UPDATE mreply "
					+ "SET msg=? "
					+"WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, msg);
			ps.setInt(2, rno);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
}
