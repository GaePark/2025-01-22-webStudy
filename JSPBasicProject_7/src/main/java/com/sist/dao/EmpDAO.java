package com.sist.dao;
/*
 * DAO : 오라클연동
 * VO : 데이터를 모아서 한번에 전송
 * Model : JSP에서 데이터를 관리 => 자바로 변경
 * ---------------------------------------------- + Model
 * ㅣ데이터를 오라클에서 읽어서 JSP로 전송
 * jsp
 * 첫줄에
 * 	<%
 * 		사용자가 전송한 데이터 받기
 * 		오라클 연동
 * 		출력에 필요한 데이터를 가지고 온다
 * 	%>
 * ------- 완전히 자바를 분리 => controller(Servlet)
 * => 브라우저에서 호출이 가능한 파일
 *    ------------------------------- JSP / Servlet
 *    ------------------------ + Model
 *    
 *    Controller : 집중
 *    	ㅣ분리=> MSA => 기능별 서버
 *    
 *    배포(AWS) => 단점 : 수정하면 => 지우고 다시 올려야함
 *    						CI/CD
 */
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.vo.*;
/*
 * DBCP
 * ---- 톰캣
 *      ---- 연결에 소모되는 시간을 줄이기 위해서 미리 연결
 *      -- Connection객첼ㄹ 제한할 수 있다
 *      
 *      1.Connection객체 생성 ==> 메모리에 저장(POOL)
 * 			2. 사용자가 요청시마다 오라클에 연결된 Connection의 주소를 가져온다
 * 					=> getConnection()
 * 			3. Connection을 이용해서 요청 처리 => 결과값
 * 					=> 사용자 정의 (기능처리)
 * 			4. 다시 POOL안으로 변환 => 재사용
 * 					=> disConnection()
 * 	=> MyBatis에서는 자동화 처리
 * 		<dataSource type="POOLED">
 */
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public void getConnection()
	{
		try {
			Context init = new InitialContext();
			Context c = (Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
		
			conn=ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void disConnection()
	{
		try {
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public List<EmpVO> empListData()
	{
		List<EmpVO> list= new ArrayList<EmpVO>();
		
		try {
			getConnection();
			String sql ="SELECT empno,ename,job,hiredate,sal "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);
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
		}finally
		{
			disConnection();
		}
		return list;
	}

}
