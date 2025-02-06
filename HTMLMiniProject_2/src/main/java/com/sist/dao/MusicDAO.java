package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static MusicDAO dao;
	
	public MusicDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static MusicDAO newInstance()
	{
		if(dao==null)
			dao= new MusicDAO();
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
	
	//리스트
	public List<MusicVO> MusicListData(int page)
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		
		try {
			getConnection();
			int pageSize=12;
			int start = (pageSize*page)-(pageSize-1);
			int end = (pageSize*page);
			
			String sql = "SELECT mno, title,poster,num "
					+ "FROM (SELECT mno,title,poster,rownum AS num "
					+ "FROM (SELECT mno,title,poster "
					+ "FROM genie_music "
					+ "ORDER BY mno)) "
					+ "WHERE num BETWEEN ? AND ? ";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster("https:"+rs.getString(3));
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
	//총페이지
	public int musicTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/12.0) FROM genie_music";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next(); 
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return total;
	}
	
	//타입
	public List<MusicVO> MusicTypeData(int page, int cno)
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		
		try {
			getConnection();
			int pageSize=12;
			int start = (pageSize*page)-(pageSize-1);
			int end = (pageSize*page);
			
			String sql = "SELECT mno, title,poster,num "
					+ "FROM (SELECT mno,title,poster,rownum AS num "
					+ "FROM (SELECT mno,title,poster "
					+ "FROM genie_music "
					+ "WHERE cno="+cno
					+ " ORDER BY mno)) "
					+ "WHERE num BETWEEN ? AND ? ";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster("https:"+rs.getString(3));
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
	//타입별
	public int musicTypeTotalPage(int cno)
	{
		int total=0;
		try {
			getConnection();
			String sql = "SELECT CEIL(count(*)/12.0) FROM genie_music WHERE cno="+cno;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return total;
	}
	//디테일
	/*
CNO	NUMBER
TITLE	VARCHAR2(1000 BYTE)
SINGER	VARCHAR2(500 BYTE)
ALBUM	VARCHAR2(500 BYTE)
POSTER	VARCHAR2(260 BYTE)
IDCREMENT	NUMBER
STATE	VARCHAR2(30 BYTE)
KEY	VARCHAR2(200 BYTE)
HIT	NUMBER
	 */
	public MusicVO musicDetailData(int mno)
	{
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			String sql = "UPDATE genie_music SET "
					+ "hit=hit+1 "
					+ "WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			sql="SELECT cno,title,singer,album,poster,idcrement,state,hit "
					+ "FROM genie_music "
					+ "WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setCno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSinger(rs.getString(3));
			vo.setAlbum(rs.getString(4));
			vo.setPoster(rs.getString(5));
			vo.setIdcrement(rs.getInt(6));
			vo.setState(rs.getString(7));
			vo.setHit(rs.getInt(8));
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return vo;
	}
	
	public MusicVO musicCookieData(int mno)
	{
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			String sql = "SELECT mno,title,poster "
					+ "FROM genie_music "
					+ "WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return vo;
	}
	public List<MusicVO> musicFindData(String col, String find, int page)
	{
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			int pageSize = 20;
			int start = (pageSize*page)-(pageSize-1);
			int end = (pageSize*page);
			String sql = "SELECT mno,title,poster,num "
					+ "FROM (SELECT mno,title,poster,rownum AS num "
					+ "FROM (SELECT mno,title,poster "
					+ "FROM genie_music "
					+ "WHERE "+col+" LIKE '%'||?||'%')) "
					+ "WHERE num BETWEEN ? AND ?";
			ps= conn.prepareStatement(sql);
			ps.setString(1, find);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo = new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
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
	public int musicFindTotalPage(String col, String find)
	{
		int total=0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/20.0) FROM genie_music "
					+ "WHERE "+col+" LIKE '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, find);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return total;
	}
	public MemberVO memberLogin(String id, String pwd)
	{
		MemberVO vo = new MemberVO();
		try {
			getConnection();
			String sql = "SELECT count(*) FROM member WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int iCheck = rs.getInt(1);
			rs.close();
			if(iCheck!=1)
			{
				vo.setMsg("NOID");
			}
			else
			{
				sql="SELECT id,name,sex,pwd FROM member WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,id);
				rs=ps.executeQuery();
				rs.next();
				if(rs.getString(4).equals(pwd))
				{
					vo.setMsg("OK");
				}
				else
				{
					vo.setMsg("NOPWD");
					
				}
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setSex(rs.getString(3));
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return vo;
	}
}
