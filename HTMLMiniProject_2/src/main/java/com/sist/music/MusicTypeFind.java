package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sist.dao.MusicDAO;
import com.sist.vo.MusicVO;


@WebServlet("/MusicTypeFind")
public class MusicTypeFind extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charSet=UTF-8");
		
		PrintWriter out= response.getWriter();
		
		String page = request.getParameter("page");
		String cno = request.getParameter("cno");
		
		if(page==null) page="1";
		if(cno==null) cno="1";
		int curpage = Integer.parseInt(page);
		int ncno=Integer.parseInt(cno);
		
		MusicDAO dao = MusicDAO.newInstance();
		
		int totalpage= dao.musicTypeTotalPage(ncno);
		List<MusicVO> list = dao.MusicTypeData(curpage, ncno);
		

		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		
		out.println("<div class=\"row text-center\" style=\"margin-bottom:20px;\">");
		out.println("<a href=\"MainServlet?mode=3&page=1&cno=1\" class=\"btn btn-sm btn-danger\">TOP 50</a>");
		out.println("<a href=\"MainServlet?mode=3&page=1&cno=2\" class=\"btn btn-sm btn-success\">가요</a>");
		out.println("<a href=\"MainServlet?mode=3&page=1&cno=3\" class=\"btn btn-sm btn-info\">POP</a>");
		out.println("<a href=\"MainServlet?mode=3&page=1&cno=4\" class=\"btn btn-sm btn-primary\">OST</a>");
		out.println("<a href=\"MainServlet?mode=3&page=1&cno=5\" class=\"btn btn-sm btn-warning\">트롯</a>");
		out.println("<a href=\"MainServlet?mode=3&page=1&cno=6\" class=\"btn btn-sm btn-default\">JAZZ</a>");
		out.println("<a href=\"MainServlet?mode=3&page=1&cno=7\" class=\"btn btn-sm\" style=\"background:black; color:white;\">CLASSIC</a>");
		out.println("</div>");
		
		out.println("<div class=row>");
		for(MusicVO vo: list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"MusicBeforeDetail?mno="+vo.getMno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:150px\">");
			out.println("<div class=\"caption\">");
			out.println("<p>"+vo.getTitle()+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
		
		out.println("</div>");
		out.println("<div class=\"row text-right\">");
		out.println("<ul class=\"pagination\">");
		if(curpage>1)
			out.println("<li><a href=\"MainServlet?mode=3&cno="+ncno+"&page="+(curpage-1)+"\">이전</a></li>");
		if(curpage<totalpage)
			out.println("<li><a href=\"MainServlet?mode=3&cno="+ncno+"&page="+(curpage+1)+"\">다음</a></li>");
			
		out.println("</ul>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
