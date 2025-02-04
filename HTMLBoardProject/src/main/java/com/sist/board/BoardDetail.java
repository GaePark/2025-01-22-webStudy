package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sist.vo.*;
import com.sist.dao.*;


@WebServlet("/BoardDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 브라우저에 전송 방식
		response.setContentType("text/html; charset=UTF-8");
		//2. HTML을 읽어갈 브라우저 정보
		PrintWriter out = response.getWriter();
		//3. 사용자가 보낸 데이터 받기
		String no=request.getParameter("no");
		//4. 데이터베이스 연동
		BoardDAO dao = BoardDAO.newInstance();
		
		//5. 데이터 읽기
		BoardVO vo = dao.boardDetailData(Integer.parseInt(no));
		//6. HTML을 이용해서 화면에 데이터 출력
		out.println("<html>");
		out.println("<body>");
		out.println("<link rel=stylesheet href=css/table.css />");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>내용보기</h1>");
		out.println("<table class=table_content width=600>");
		out.println("<tr>");
		out.println("<th 20% align=center>번호</th> ");
		out.println("<td width:30%>"+vo.getNo()+"</td>");
		out.println("<th 20% align=center>작성일</th> ");
		out.println("<td width:30%>"+vo.getDbday()+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th 20% align=center>이름</th> ");
		out.println("<td width:30%>"+vo.getName()+"</td>");
		out.println("<th 20% align=center>조회수</th> ");
		out.println("<td width:30%>"+vo.getHit()+"</td>");
		out.println("</tr>");
		out.println("<th 20% align=center>재목</th> ");
		out.println("<td colspan=3>"+vo.getSubject()+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan=4 valign=top align=left height=200>");
		out.println("<pre style=\"white-space:pre-wrap\">"+vo.getContent()+"</pre>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan=4 align=right>");
		out.println("<a href=#>수정</a>");
		out.println("<a href=BoardDelete?no="+vo.getNo()+">삭제</a>");
		out.println("<a href=BoardList>목록</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
