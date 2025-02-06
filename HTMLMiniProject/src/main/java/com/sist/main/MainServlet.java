package com.sist.main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//사용자 요청 페이지를로 변경
		String mode = request.getParameter("mode");
		if(mode==null)
			mode="1";
		String page=ChangeServlet.pageChange(Integer.parseInt(mode));
		out.println("<head>");
		out.println("<html>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
		out.println("</head>");
		out.println("<body>");
		String html="<nav class=\"navbar navbar-inverse\">"
				+ "  <div class=\"container-fluid\">"
				+ "    <div class=\"navbar-header\">"
				+ "      <a class=\"navbar-brand\" href=\"MainServlet\">Food & Music</a>"
				+ "    </div>"
				+ "    <ul class=\"nav navbar-nav\">"
				+ "      <li class=\"active\"><a href=\"MainServlet\">Home</a></li>"
				+ "      <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">맛집<span class=\"caret\"></span></a>"
				+ "        <ul class=\"dropdown-menu\">"
				+ "          <li><a href=\"MainServlet?mode=3\">맛집검색 1</a></li>"
				+ "          <li><a href=\"MainServlet?mode=4\">FoodFind 2</a></li>"
				+ "        </ul>"
				+ "      </li>"
				+ "      <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">뮤직<span class=\"caret\"></span></a>"
				+ "        <ul class=\"dropdown-menu\">"
				+ "          <li><a href=\"MainServlet?mode=5\">뮤직목록</a></li>"
				+ "          <li><a href=\"MainServlet?mode=7\">뮤직검색 1</a></li>"
				+ "          <li><a href=\"MainServlet?mode=8\">뮤직검색 2</a></li>"
				+ "        </ul>"
				+ "      </li>"
				+ "      <li><a href=\"#\">게시판</a></li>"
				+ "      <li><a href=\"#\">기타</a></li>"
				+ "    </ul>"
				+ "  </div>"
				+ "</nav>";
		out.println(html);
		
		HttpSession session = request.getSession();
		/*
		 * Session VS Cookie
		 * => 저장하는 역할은 동일
		 * => 서버에 저장 / 클라이언트 브라우저에 저장
		 * => 보안이 뛰어나다 / 보안이 취약함
		 * => Object         / String
		 * => 로그인 처리 시 =>사용자의 일부 정보를 저장
		 * => 예약시 선택한 내용 / 장바구니 저장
		 * => 비밀번호 : 암호화
		 * 
		 * => 주요 메소드 => 같은 브라우저에서는 사용자당 1개만 세션사용이 가능
		 * 	저장 => setAttribute()
		 * 	읽기 => getAttribute()
		 * 	삭제 => removeAttribute()
		 * 	전체 삭제 => invalidate() => 로그아웃
		 * 
		 * 	=> 값이 없는 경우 => session에 로그인이 안된 상태
		 * 		null
		 * 
		 * 	=> 회원가입 => 로그인
		 */
		/*
		session.setAttribute("id", "hong");
		session.setAttribute("name", "홍길동");
		*/
		
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		out.println("<div class=containter>");
		out.println("<div class=\"row text-right\">");
		if(id==null)//  로그인이 안된상태
		{
			// 로그인 창
			
			out.println("<form method=post action=LoginServlet>");
			out.println("ID:");
			out.println("<input type=text name=id size=15 class=input-sm required/>");
			out.println("Password:");
			out.println("<input type=password name=pwd size=15 class=input-sm required/>");
			out.println("<input type=submit value=로그인 class=\"btn btn-sm btn-danger\"/>");
			
			out.println("</form>");
		}
		else // 로그인이 된 상태
		{
			out.println("<form method=get action=LoginServlet>");
			//로그아웃
			out.println(name+"님 로그인 중입니다!!");
			out.println("<input type=submit value=로그아웃 class=\"btn btn-sm btn-success\"/>");
			
			out.println("</form>");
		}
		
		out.println("</div>");
		out.println("</div>");
		
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.include(request, response);
		out.println("</body>");
		out.println("</html>");
	}

}
