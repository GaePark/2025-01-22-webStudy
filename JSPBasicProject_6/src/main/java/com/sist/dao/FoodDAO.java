package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import com.sist.dao.*;
import javax.naming.*;
/*
 * JSP
 * ---
 * 	=> 화면출력
 * 	=> 자바 HTML 분리
 *  => 내장객체
 *  	=> request/response/application/session
 *  	=> cookie사용법
 *  => jsp액션태그
 *  	<jsp:include>
 *  => JDBC / DBCP
 *  => JSTL / EL
 *  	<c:forEach> , <cLforTokens>,<c:if>
 *  	<c:set>
 *  	<fmt:formatDate> <fmt:formatNumber>
 *  	TO_CHAR(regdate,'')
 *  ------------------------------------------------
 *  MVC
 */
public class FoodDAO {
	private PreparedStatement ps;
	private Connection conn;
	
	public void getConnection()
	{
		try {
			//미리 생성된 
			Context init = new InitialContext();
			Context c=(Context) init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
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
	//목록 출력 => Cookie => 301페이지
	
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
	public FoodVO foodDetailData(int fno, int mode)
	{
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			String sql="";
			if(mode==1)
			{
			sql = "UPDATE food_menupan "
					+ "SET hit=hit+1 "
					+ "WHERE fno="+fno;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			}
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
	//상세보기 => 지도 API
	
}
