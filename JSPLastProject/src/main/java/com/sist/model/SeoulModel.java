package com.sist.model;
import java.util.*;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class SeoulModel {
	private String[] tab={"","seoul_location","seoul_nature","seoul_shop","seoul_food"};
	@RequestMapping("seoul/seoul_list.do")
	public String seoul_list(HttpServletRequest request, HttpServletResponse response)
	{
//		request.getParameterNames()
		{
			String page = request.getParameter("page");
			String mode = request.getParameter("mode");
			if(page==null) page="1";
			int curpage = Integer.parseInt(page);
			Map map = new HashMap();
			map.put("start", (12*curpage)-11);
			map.put("end", (12*curpage));
			map.put("table_name", tab[Integer.parseInt(mode)]);
			
			final int BLOCK = 10;
			int startPage = ((curpage-1))/BLOCK*BLOCK+1;
			int endPage = ((curpage-1))/BLOCK*BLOCK+10;
			int totalpage = SeoulDAO.seoulTotalPage(map);
			if(endPage>totalpage) endPage=totalpage;
				
			List<SeoulVO> list = SeoulDAO.seoulListData(map);
			request.setAttribute("list", list);
			request.setAttribute("totalpage", totalpage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("curpage", curpage);
			request.setAttribute("main_jsp", "../seoul/seoul_list.jsp");
			request.setAttribute("mode", mode);
			return "../main/main.jsp";
		}
	}
}
