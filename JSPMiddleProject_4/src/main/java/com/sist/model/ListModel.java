package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
public class ListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
			String page= request.getParameter("page");
			if(page==null) page="1";
			int curpage = Integer.parseInt(page);
			int rowSize = 10;
			int start = (rowSize*curpage)-(rowSize-1);
			int end = (rowSize*curpage);
			Map map = new HashMap();
			map.put("start", start);
			map.put("end", end);
			map.put("page", curpage);
			
			List<BoardVO> list = BoardDAO.boardListData(map);
			int totalpage = BoardDAO.boardTotalPage();
			
			request.setAttribute("list", list);
			request.setAttribute("curpage", curpage);
			request.setAttribute("totalpage", totalpage);
			
			
			
			return "board/list.jsp";
	}

}
