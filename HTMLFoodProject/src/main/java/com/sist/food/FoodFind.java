package com.sist.food;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/FoodFind")
public class FoodFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
/*
 * doGet
 * {
 * 화면 출력
 * }
 * doPost()
 * {
 * 	검색어를 받는다 => 데이터 연동
 * }
 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=URF-8");
		
		PrintWriter out = response.getWriter();
		
		String page = request.getParameter("page");
		String col = request.getParameter("col");
		String find = request.getParameter("find");
	}

}
