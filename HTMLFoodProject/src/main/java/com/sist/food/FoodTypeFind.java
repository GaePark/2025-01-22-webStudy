package com.sist.food;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import com.sist.vo.*;
import com.sist.dao.*;

/*
 * 1. 사용자의 전송방식
 * 	GET / POST
 * 	ㅣ<a> , sendRedirect()=> URL?데이터전송
 *  ㅣ<form> , ajax , axios.post()
 *  
 *  검색어 입력 / 찾는 컬럼 => 두개를 동시에 전송
 *  ---------------------------------------------<form> => POST
 *  처음 출력은 => GET
 *  
 *  Get => doGet() ==> @GetMapping()
 *  Post => doPost() ==> @PostMapping()
 *  Get/Post => service() @RequestMapping()
 *  						=> Spring 6.0에서는 비권장
 * JSP => doGet/doPost가 없다
 * 		  _jspService()
 */

@WebServlet("/FoodTypeFind")
public class FoodTypeFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");
		String strpage = request.getParameter("page");
		if(type==null) type="한식";
		if(strpage==null) strpage="1";
		int curpage = Integer.parseInt(strpage);
		
		FoodDAO dao = FoodDAO.newInstance();
		List<FoodVO> list= dao.foodTypeData(type, curpage);
		int totalPage=dao.foodTypeTotalPage(type);
		
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
		
		out.println("<div class=\"row text-center\" style=\"margin-bottom:20px;\">");
		out.println("<a href=FoodTypeFind?type=한식 class=\"btn btn-sm btn-danger\">한식</a>");
		out.println("<a href=FoodTypeFind?type=양식 class=\"btn btn-sm btn-success\">양식</a>");
		out.println("<a href=FoodTypeFind?type=중식 class=\"btn btn-sm btn-info\">중식</a>");
		out.println("<a href=FoodTypeFind?type=일식 class=\"btn btn-sm btn-primary\">일식</a>");
		out.println("<a href=FoodTypeFind?type=카페 class=\"btn btn-sm btn-warning\">카페</a>");
		out.println("<a href=FoodTypeFind?type=기타 class=\"btn btn-sm btn-default\">기타</a>");
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
			out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+(startPage-1)+"\">&lt;</a></li>");
		
		for(int i=startPage;i<=endPage;i++) {
			if(i==curpage)
				out.println("<li class=active><a href=\"#\">"+i+"</a></li>");
			else
			out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+i+"\">"+i+"</a></li>");
			
		}

		if(endPage<totalPage)
			out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+(endPage+1)+"\">&gt;</a></li>");
			
		out.println("</ul>");
		out.println("</div>");
	
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
