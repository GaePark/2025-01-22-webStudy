package com.sist.food;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/ReplyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rno = request.getParameter("rno");
		String fno = request.getParameter("fno");
		String msg = request.getParameter("msg");
		
		ReplyDAO dao = ReplyDAO.newInstance();
		dao.replyUpdate(Integer.parseInt(rno), msg);
		
		response.sendRedirect("MainServlet?mode=2&fno="+fno);
	}

}
