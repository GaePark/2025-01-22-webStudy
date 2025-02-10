package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
/*
 * Servlet / JSP
 * ------- ------
 * 	      ㅣHTML화면 (View)
 *  ㅣ보안이 뛰어나다 => 배포 (.class)
 *  	=> 로직 , 보안 => 연결용 , 라이브러리 = HTML을 출력하지 않는다
 *  							   ========== 스프링
 *  ㅣHTML 구현부분이 어렵다
 *  ㅣHTML을 약간 수정시마다 => 컴파일 / 실행
 *  ---------------------------------------------------------------
 *  JSP
 *  => 자바 + HTML : 구분이 어렵다
 *     -----------
 *      자바 ---(Model)
 *             ㅣ------서블릿(Controller) => MVC(*****)
 *      HTML ---(View)
 *      ** Spring은 MVC구조만 사용이 가능
 *      ** URL => 확장자 => .jsp => 재사용이 없다
 *         -------------- 사용자.do / .naver
 * => 1. SQL 문장이 없다
 * => 2. HTML에 제어문이 가능(JSP가 필요 없다)
 *       Vue/React/ThymeLeaf
 * => Front / Back => MSA
 * 
 *
 */
public class ReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static ReplyDAO dao;
	
	public ReplyDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
	}
	
	public static ReplyDAO newInstance() {
		if(dao==null)
			dao=new ReplyDAO();
		return dao;
	}
	
	public void getConnection()
	{
		try {
			conn= DriverManager.getConnection(URL,"hr","happy");
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
	//DML => SELECT / UPDATE / DELETE / INSERT
	/*
	 * RNO	NUMBER
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
			String sql = "INSERT INTO reply VALUES("
					+ "reply_rno_seq.nextval,?,?,?,?,SYSDATE)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,vo.getFno());
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
	//댓글 정보 다 가져오기
	/*
RNO	NUMBER
FNO	NUMBER
ID	VARCHAR2(20 BYTE)
NAME	VARCHAR2(51 BYTE)
MSG	CLOB
REGDATE	DATE
	 */
	public List<ReplyVO> replyListData(int fno){
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql = "SELECT rno,fno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
					+ "FROM reply "
					+ "WHERE fno="+fno
					+ "ORDER BY rno DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				ReplyVO vo = new ReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setFno(rs.getInt(2));
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
			String sql = "DELETE reply WHERE rno="+rno;
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
			String sql = "UPDATE reply "
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
