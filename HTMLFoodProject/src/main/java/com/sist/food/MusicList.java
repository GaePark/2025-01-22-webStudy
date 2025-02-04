package com.sist.food;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dao.*;
/**
 * Servlet implementation class MusicList
 */
@WebServlet("/MusicList")
public class MusicList extends HttpServlet {
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
		
		int totalpage= dao.musicTotalPage(ncno);
		List<MusicVO> list = dao.MusicListData(curpage, ncno);
		
		final int BLOCK = 5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<ul>");
		out.println("<a href=\"MusicList?page=1&cno=1\"><li >TOP 200</li></a>");
		out.println("<a href=\"MusicList?page=1&cno=2\"><li >가요</li></a>");
		out.println("<a href=\"MusicList?page=1&cno=3\"><li >POP</li></a>");
		out.println("<a href=\"MusicList?page=1&cno=4\"><li >OST</li></a>");
		out.println("<a href=\"MusicList?page=1&cno=5\"><li >트롯</li></a>");
		out.println("<a href=\"MusicList?page=1&cno=6\"><li >JAZZ</li></a>");
		out.println("<a href=\"MusicList?page=1&cno=7\"><li >CLASSIC</li></a>");
		out.println("</ul>");
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
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		if(startPage>1)
			out.println("<li><a href=\"MusicList?page="+(startPage-1)+"\">&lt;</a></li>");
		
		for(int i=startPage;i<=endPage;i++) {
			if(i==curpage)
				out.println("<li class=active><a href=\"#\">"+i+"</a></li>");
			else
			out.println("<li><a href=\"MusicList?page="+i+"&cno="+ncno+"\">"+i+"</a></li>");
			
		}

		if(endPage<totalpage)
			out.println("<li><a href=\"MusicList?page="+(endPage+1)+"\">&gt;</a></li>");
			
		out.println("</ul>");
		out.println("</div>");
		out.println("<div class=row>");
		out.println("<h3>최근 본 뮤직</h3>");
		out.println("<hr>");
		
		List<MusicVO> cList = new ArrayList<MusicVO>();
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("music_"))
				{
					String mno=cookies[i].getValue();
					MusicVO vo = dao.musicCookieData(Integer.parseInt(mno));
					cList.add(vo);
				}
			}
		}
		int p=0;
		for(MusicVO vo:cList)
		{
			if(p>8) break;
			out.println("<a href=MusicDetail?mno="+vo.getMno()+">");
			out.println("<img src="+vo.getPoster()+" class=img-rounded style=\"width:100px; height:100px cursor:pointer;\" title=\""+vo.getTitle()+"\" />");
			out.println("</a>");
			p++;
			
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
