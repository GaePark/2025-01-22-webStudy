package com.sist.model;
import java.util.*;
import java.sql.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;

public class FoodModel {
	public void foodListData(HttpServletRequest request) {
		
		
		String strPage=request.getParameter("page");
		if(strPage==null) strPage="1";
		int curpage=Integer.parseInt(strPage);
		FoodDAO dao = new FoodDAO();
		
		List<FoodVO> list = dao.foodListDAta(curpage);
		int totalpage= dao.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		/*
		1 2 3 ... 10
		-         --
	 startPage   endPage
		 */
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("mian_jsp", "home.jsp");
	}



}
