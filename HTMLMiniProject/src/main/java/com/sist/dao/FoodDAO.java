package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.vo.*;

public class FoodDAO {
	private PreparedStatement ps;
	private Connection conn;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static FoodDAO dao;
	
	
	//1. 드라이버 등록
	public FoodDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	// 2. 싱글턴
	public static FoodDAO newInstance() {
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	//3. 오라클 연결
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//4. 오라클 닫기
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//기능
	//목록
	public List<FoodVO> foodListDAta(int page)
	{
		List<FoodVO> list= new ArrayList<FoodVO>();
		try {
			int pageSize = 12;
			int start = (pageSize*page)-(pageSize-1);
			int end = pageSize*page;
			
			getConnection();
			String sql = "SELECT fno,name,poster,num "
					+ "FROM (SELECT fno,name,poster,rownum AS num "
					+ "FROM (SELECT /*+ INDEX_ASC(food_menupan fm_fno_pk)*/ fno,name,poster "
					+ "FROM food_menupan)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https:/www.menupan.com"+rs.getString(3));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			disConnection();
		}
		return list;
	}
	//총 페이지
	public int foodTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM food_menupan";
			ps= conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			disConnection();
		}
		
		return total;
	}
	//상세보기
	/*
 FNO  		NUMBER
 NAME 		VARCHAR2(500)
 TYPE  		VARCHAR2(100)
 PHONE  	VARCHAR2(20)
 ADDRESS  	VARCHAR2(700)
 SCORE  	NUMBER(2,1)
 THEME  	CLOB
 POSTER 	VARCHAR2(300)
 IMAGES  	VARCHAR2(4000)
 TIME     	VARCHAR2(100)
 PARKING 	VARCHAR2(200)
 CONTENT  	CLOB
 HIT  		NUMBER
 PRICE   	VARCHAR2(30)
	 */
	public FoodVO foodDetailData(int fno)
	{
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			String sql = "UPDATE food_menupan "
					+ "SET hit=hit+1 "
					+ "WHERE fno="+fno;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			 sql = "SELECT fno,name,type,phone,address,score,theme,poster,images,time,parking,content,hit,NVL(price,'정보 없음') "
					+ "FROM food_menupan "
					+ "WHERE fno="+fno;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setType(rs.getString(3));
			vo.setPhone(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setScore(rs.getDouble(6));
			vo.setTheme(rs.getString(7));
			vo.setPoster("https://www.menupan.com"+rs.getString(8));;
			vo.setImages(rs.getString(9));
			vo.setTime(rs.getString(10));
			vo.setParking(rs.getString(11));
			vo.setContent(rs.getString(12));
			vo.setHit(rs.getInt(13));
			vo.setPrice(rs.getString(14));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
		return vo;
	}
	//Cookie데이터
	public FoodVO foodCookieData(int fno)
	{
		FoodVO vo = new  FoodVO();
		try {
			getConnection();
			String sql= "SELECT fno,name,poster "
					+ "FROM food_menupan "
					+ "WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPoster("https://www.menupan.com"+rs.getString(3));
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
	
	//타입검색
	public List<FoodVO> foodTypeData(String type, int page)
	{
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			int pageSize =12;
			int start = (pageSize*page)-(pageSize-1);
			int end = (pageSize*page);
			String sql="";
			if(type.equals("기타"))
			{
				sql = "SELECT fno,name,poster,type,num "
						+ "FROM (SELECT fno,name,poster,type,rownum AS num "
						+ "FROM (SELECT /*+INDEX_ASC(food_menupan fm_fno_pk)*/ fno,name,poster,type "
						+ "FROM food_menupan "
						+ "WHERE NOT REGEXP_LIKE(type,'한식|양식|중식|일식|카페'))) "
						+ "WHERE num BETWEEN ? AND ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
			}
			else
			{
			sql = "SELECT fno,name,poster,type,num "
					+ "FROM (SELECT fno,name,poster,type,rownum AS num "
					+ "FROM (SELECT /*+INDEX_ASC(food_menupan fm_fno_pk)*/ fno,name,poster,type "
					+ "FROM food_menupan WHERE REGEXP_LIKE(type,?))) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ps.setInt(2, start);
			ps.setInt(3, end);
			}
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
				vo.setType(rs.getString(4));
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
	//타입별 토탈 페이지
	public int foodTypeTotalPage(String type)
	{
		int total=0;
		try {
			getConnection();
			String sql="";
			if(!type.equals("기타"))
			{
				sql="SELECT CEIL(COUNT(*)/12.0) FROM food_menupan WHERE REGEXP_LIKE(type,?)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, type);
			}
			else
			{
				sql="SELECT CEIL(COUNT(*)/12.0) FROM food_menupan "
						+ "WHERE NOT REGEXP_LIKE(type,'한식|양식|일식|중식|카페')";
				ps=conn.prepareStatement(sql);
			}
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	// 이름 검색
	public List<FoodVO> foodFind(int page,String col,String fd)
	{
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			int pageSize = 20;
			int start = (pageSize*page)-(pageSize-1);
			int end = (pageSize*page);
			String sql = "SELECT fno,name,poster,address,type,num "
					+ "FROM (SELECT fno,name,poster,address,type,rownum AS num "
					+ "FROM (SELECT/*+INDEX_ASC(food_menupan fm_fno_pk) */ fno,name,poster,address,type "
					+ "FROM food_menupan "
					+ "WHERE "+col+" LIKE '%'||?||'%')) "
					+ "WHERE ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, fd);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster("https://www.menupan.com"+rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setType(rs.getString(5));
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
	public int foodFindTotalPage(String col, String fd)
	{
		int total=0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/20.0) FROM food_menupan "
					+ "WHERE "+col+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, col);
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
	
	public MemberVO memberLogin(String id,String pwd)
	{
		MemberVO vo = new MemberVO();
		try {
			getConnection();
			String sql = "SELECT COUNT(*) FROM member "
					+ "WHERE id=?";
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
			else {
				sql="SELECT id,name,sex,pwd "
						+ "FROM member "
						+ "WHERE id=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				rs.next();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setSex(rs.getString(3));
				String db_pwd=rs.getString(4);
				if(db_pwd.equals(pwd))
				{
					vo.setMsg("OK");
				}
				else
				{
					vo.setMsg("NOPWD");
				}
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
