package com.sist.dao;
import java.util.*;
import java.sql.*;
//DBCP => 웹 프로그램의 일반화 => MyBatis
public class DataBoardDAO {
	PreparedStatement ps;
	Connection conn;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static DataBoardDAO dao;
	
	public DataBoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static DataBoardDAO newInstance()
	{
		if(dao==null)
			dao= new DataBoardDAO();
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
			if(ps !=null) ps.close();
		}catch(Exception e) {}
	}
	
	//기능
	/*
NO	NUMBER
NAME	VARCHAR2(51 BYTE)
SUBJECT	VARCHAR2(2000 BYTE)
CONTENT	CLOB
PWD	VARCHAR2(10 BYTE)
REGDATE	DATE
HIT	NUMBER
FILENAME	VARCHAR2(260 BYTE)
FILESIZE	NUMBER
	 */
	// JDBC => DBCP =>MyBatis(XML(JSP),Annotation(Spring) => JPA
	/*
	 * 과정
	 * JSP활용 => 18일
	 * ---------------
	 * Spring : MVC =>SpringFramework 직접 개발
	 */
	//목록 + 총페이지
	public List<DataBoardVO> dataBoardListData(int page)
	{
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		try {
			getConnection();
			int pageSize = 10;
			int start = (page*pageSize)-(pageSize-1);
			int end = (page*pageSize);
			String sql = "SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD'),hit, num "
					+ "FROM (SELECT no,name,subject,regdate,hit,rownum AS num "
					+ "FROM (SELECT no,name,subject,regdate,hit "
					+ "FROM jspDataBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				DataBoardVO vo = new DataBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	
		return list;
	}
	
	public int databoardTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM jspDataBoard";
			ps= conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return total;
	}
	// 상세보기 => 다운로드
	public DataBoardVO dataBoardDetailData(int no)
	{
		DataBoardVO vo = new DataBoardVO();
		try {
			getConnection();
			String sql="UPDATE jspDataBoard SET "
					+ "hit=hit+1 "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql = "SELECT no,name,subject,content,TO_CHAR(regdate,'YYY-MM-DD'),filename,filesize,hit "
					+ "FROM jspDataBoard "
					+ "WHERE no="+no;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setFilename(rs.getString(6));
			vo.setFilesize(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return vo;
	}
	//추가 => 업로드
	public void dataBoardInsert(DataBoardVO vo)
	{
		try {
			getConnection();
			String sql = "INSERT INTO jspDataBoard "
					+ "VALUES(jbd_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
}

// 수정
// 삭제 => 댓글 먼저 삭제 => 게시물
