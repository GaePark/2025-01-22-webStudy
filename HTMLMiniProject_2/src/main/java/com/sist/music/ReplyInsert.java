package com.sist.music;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import com.sist.dao.*;
import com.sist.vo.*;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ReplyInsert")
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String mno=request.getParameter("mno");
		String rno=request.getParameter("rno");

				ReplyDAO dao = ReplyDAO.newInstance();
				dao.replyDelete(Integer.parseInt(rno));
				
				response.sendRedirect("MainServlet?mode=2&mno="+mno);

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno=request.getParameter("mno");
		String msg=request.getParameter("msg");
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		ReplyVO vo = new ReplyVO();
		vo.setMno(Integer.parseInt(mno));
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		
		ReplyDAO dao = ReplyDAO.newInstance();
		dao.replyInsert(vo);
		response.sendRedirect("MainServlet?mode=2&mno="+mno);
	}

}
