package com.sist.food;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import com.sist.vo.*;
import com.sist.dao.*;
import java.util.*;
/*
 * init() => 변수에 초기화
 *           web.xml파일 읽기, 자동 로그인
 * 사용자 요청시마다 호출
 *  ㅣ
 *  doGet
 *  doPost
 *  ------- 두개를 합친것 => service => _jspService() => JSP파일은 사용자에서 
 *  ㅣ
 *  메모리 해제
 *  destory() => 화면이동 / 브라우저 종료/ 새로고침 => _jspDestory
 *  
 *  request / response / session / cookie
 *  ----------------------------   -------
 *  ㅣ톰캣이 관리                  브라우저 관리
 */
@WebServlet("/FoodFind")
public class FoodFind extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String strpage = request.getParameter("page");
		if(strpage==null) strpage="1";
		int curpage = Integer.parseInt(strpage);
		String column=request.getParameter("column");
		String fd=request.getParameter("fd");
		
		FoodDAO dao = FoodDAO.newInstance();
		List<FoodVO> list= dao.foodFind(curpage,column, fd);
		int totalPage=dao.foodFindTotalPage(column,fd);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalPage)
			endPage=totalPage;
		
		
		out.println("<head>");
		out.println("<html>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		
		out.println("<div class=\"row\" style=\"margin-bottom:20px;\">");
		out.println("<form method=post action=MainServlet?mode=4>");
		out.println("<select name=column class=input-sm>");
		out.println("<option value=name>업체명</option>");
		out.println("<option value=type>음식종류</option>");
		out.println("<option value=address>주소</option>");
		out.println("/<select>");
		out.println("<input type=text name=fd size=15 class=input-sm>");
		out.println("<button class=\"btn-sm btn-danger\">검색</button>");
		out.println("</form>");
		
		out.println("</div>");
		out.println("<div class=row>");
		
		for(FoodVO vo : list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"FoodBeforeDetail?fno="+vo.getFno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:150px\">");
			out.println("<div class=\"caption\">");
			out.println("<p>"+vo.getName()+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
		
		out.println("</div>");
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		if(startPage>1)
			out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+fd+"&page="+(startPage-1)+"\">&lt;</a></li>");
		
		for(int i=startPage;i<=endPage;i++) {
			if(i==curpage)
				out.println("<li class=active><a href=\"#\">"+i+"</a></li>");
			else
			out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+fd+"&page="+i+"\">"+i+"</a></li>");
			
		}

		if(endPage<totalPage)
			out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+fd+"&page="+(endPage+1)+"\">&gt;</a></li>");
			
		out.println("</ul>");
		out.println("</div>");
	
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
