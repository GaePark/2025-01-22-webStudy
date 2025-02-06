package com.sist.music;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

import com.sist.dao.*;
import com.sist.vo.*;
import java.sql.*;

@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String mno=request.getParameter("mno");
		
		MusicDAO dao = MusicDAO.newInstance();
		MusicVO vo = dao.musicDetailData(Integer.parseInt(mno));
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td style=\"width:30%\" rowspan=6><img src="+vo.getPoster()+" style=\"width:270px,height:300px\"/></td>");
		out.println("<td >노래</td>");
		out.println("<td >"+vo.getTitle()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>순위변동</td>");
		out.println("<td width=60%>"+vo.getState()+vo.getIdcrement()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>가수</td>");
		out.println("<td width=60%>"+vo.getSinger()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>앨범</td>");
		out.println("<td width=60%>"+vo.getAlbum()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>조회수</td>");
		out.println("<td width=60%>"+vo.getHit()+"</td>");
		out.println("</tr>");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		out.println("<tr>");
		out.println("<td class=text-right colspan=2>");
		if(id!=null)
		{
		out.println("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
		out.println("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");
		}
		out.println("<a href=MainServlet?mode=5&cno="+vo.getCno()+" class=\"btn btn-xs btn-primary\">목록</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<div style=\"margin:0 auto; width:700px;height:500px\">");
		//나중에는 키값을 받아서 뮤직비디오 영상 실행할 수 있또록하자
		out.println("<iframe style=\"width:100%; height:100%\" src=\"https://youtube.com/embed/EkoS0426BeQ\" ></iframe>");
		out.println("</div>");
		
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
