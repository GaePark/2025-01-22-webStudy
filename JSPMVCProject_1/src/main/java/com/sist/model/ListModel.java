package com.sist.model;

import jakarta.servlet.http.HttpServletRequest; 
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
import java.text.*;
public class ListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		String page = request.getParameter("page");
		if(page==null) page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", (10*curpage)-9);
		map.put("end", (10*curpage));
		List<BoardVO> list = BoardDAO.boardListData(map);
		int totalpage = BoardDAO.boardTotalPage();
		
		int startpage = ((curpage-1)/10)+1;
		int endpage = ((curpage-1)/10)+1;
		if(endpage>totalpage)
			endpage=totalpage;
		
		
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("curpage", curpage);
		
		String today= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		request.setAttribute("today", today);
		
		return "board/list.jsp";
	}

}
