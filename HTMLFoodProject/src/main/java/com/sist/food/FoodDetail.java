package com.sist.food;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dao.*;

@WebServlet("/FoodDetail")
public class FoodDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		/*
		 * request
		 * 	=> 클라이언트의 정보 (ip,port)
		 * 	=> 사용자 요청 정보 (사용자가 보낸값)
		 * 		= String	getParameter() => 단읽밧
		 * 		= String[]	getParameterValues() => 다중값
		 * 			=> checkbox
		 * 		= encoding(byte[]) 전송 => decoding(원상 복귀)
		 * 			=> setCharacterEncoding("UTF-8");
		 * https://www.google.com/search?q=%EC%9E%90%EB%B0%94&oq=%EC%9E%90%EB%B0%94&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIGCAEQRRhBMgYIAhBFGEEyBggDEEUYQdIBCDE5MDVqMGo0qAIAsAIB&sourceid=chrome&ie=UTF-8
		 * 	%EC%9E%90%EB%B0%94 ===> 자바
		 * response
		 */
		//1. 사용자가 보낸 데이터 받기
		String fno= request.getParameter("fno");
		//2. 데이터베이스 연결
		FoodDAO dao = FoodDAO.newInstance();
		FoodVO vo = dao.foodDetailData(Integer.parseInt(fno));
		
		StringTokenizer st = new StringTokenizer(vo.getImages(),",");
		
		
		out.println("<head>");
		out.println("<html>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<table class=table>");
		out.println("<tr>");
		while(st.hasMoreTokens())
		{
			out.println("<td class=text-center><img src=https://www.menupan.com"+st.nextToken()+" style=\"width:100px\"/></td>");
		}
		out.println("</tr>");
		out.println("</table>");
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td width=30% class=text-center rowspan=8>");
		out.println("<img src="+vo.getPoster()+" style=\"width:270px,height:300px\">");
		out.println("</td>");
		out.println("<td colspan=2>");
		out.println("<h3>"+vo.getName()+"&nbsp;<span style=\"color:orange\">"+vo.getScore()+"</span></h3>");
		out.println("</td>");
		out.println("</tr>")
		;
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">업종</td>");
		out.println("<td width=60%>"+vo.getType()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">주소</td>");
		out.println("<td width=60%>"+vo.getAddress()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">전화번호</td>");
		out.println("<td width=60%>"+vo.getPhone()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">영업시간</td>");
		out.println("<td width=60%>"+vo.getTime()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">주차</td>");
		out.println("<td width=60%>"+vo.getParking()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">가격대</td>");
		out.println("<td width=60%>"+vo.getPrice()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">테마</td>");
		out.println("<td width=60%>"+vo.getTheme()+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("<table class=table>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getContent());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td class=text-right>");
		out.println("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
		out.println("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");
		out.println("<a href=# class=\"btn btn-xs btn-info\">예약하기</a>");
		out.println("<a href=FoodList class=\"btn btn-xs btn-primary\">목록</a>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");

		out.println("</div");
		out.println("</div");
		out.println("</body");
		out.println("</html");
	}

}
