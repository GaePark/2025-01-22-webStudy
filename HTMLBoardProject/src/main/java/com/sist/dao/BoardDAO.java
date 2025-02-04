package com.sist.dao;
//DBCO(web)  / JDBC
import java.util.*;
import java.awt.*;
import com.sist.vo.*;
import java.util.List;
import java.sql.*;
/*
 * 					자바
 * 브라우저     <============> 오라클 (게시판데이터)
 * 					연결
 * 	  ㅣ
 * HTML/CSS/JavaScript     : 화면 UI
 * 
 * 자바 => 데이터베이스 연결 : DAO
 * 		=> 브라우저 연동 => HTML전송 => Model
 * 
 * => 데이터베이스 관리 / 사용자 요청에 대한 처리
 * => 자바 / 파이썬
 *     ㅣ      ㅣ 장고
 *     스프링(JSP) => 
 */
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static BoardDAO dao;
	
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static BoardDAO newInstance()
	{
		if(dao==null)
			dao=new BoardDAO();
		return dao;
	}
	
	public void getConnection()
	{
		try {
			conn = DriverManager.getConnection(URL, "hr","happy");
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
	//1. 전체 불러오기
	public List<BoardVO> boardAllData(int page)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql = "SELECT no,name,subject, TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit, num "
					+ "FROM (SELECT no,name,subject,regdate,hit, rownum as num "
					+ "FROM (SELECT no,name,subject,regdate,hit "
					+ "FROM htmlBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowsize = 10;
			int start = (rowsize*page)-(rowsize-1);
			int end = (rowsize*page);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setNum(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	//1-1 총페이지
	public int boardTotalPage()
	{
		int total=0;
		try {
			getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM htmlBoard";
			ps= conn.prepareStatement(sql);
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
	//2. 상세보기-----------------SELECT
	public BoardVO boardDetailData(int no)
	{
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql="UPDATE htmlboard SET "
					+ "hit=hit+1"
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "SELECT no,name,subject,content, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),hit FROM htmlBoard WHERE no="+no;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	//3. 글쓰기 --------------INSERT
	public void boardInsertData(BoardVO vo)
	{
		try {
			getConnection();
			String sql= "INSERT INTO htmlBoard(no,name,subject,content,pwd) "
					+ "VALUES(hb_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			disConnection();
		}
	}
	//4. 수정-----------------UPDATE
	//5. 삭제---------------- DELETE
	public boolean boardDelete(int no, String pwd)
	{
		/*
		 * 오라클 => 사이트에 필요한 데이터 저장
		 * 	=> SQL 문장
		 * 자바 => 오라클연동 / 브라우저 연동
		 * 		   결과값을 받아서 => 브라우저로 전송
		 * 		   사용자 요청을 받는 경우
		 * 		   => 스프링 : 자바 / 코틀린
		 * 		   => ASP : C#, 장고:파이썬
		 * HTML / CSS => 브라우저에 화면만 출력
		 */
		boolean bCheck = false;
		try {
			getConnection();
			String sql="SELECT pwd FROM htmlboard WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			if(db_pwd.equals(pwd))
			{
				bCheck=true;
				sql="DELETE htmlboard WHERE no="+no;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
			}
			else {
				bCheck=false;
			}
		} catch(Exception ex){
			ex.printStackTrace();
		} finally
		{
			disConnection();
		}
		
		return bCheck;
	}
	// => 자료실 => 댓글 => 예약 => 결제 => 장바구니
	
}
