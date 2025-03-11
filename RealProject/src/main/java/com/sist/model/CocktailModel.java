package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CocktailModel {
	@RequestMapping("cocktail/cocktail_list.do")
	public String cocktail_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page = request.getParameter("page");
		if(page==null) page="1";
		int curpage= Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", (curpage*12)-11);
		map.put("end", (curpage*12));

	  List<CocktailVO> list = CocktailDAO.cocktailListData(map);
	  int totalpage = CocktailDAO.cocktailTotalPage();
	  
	  request.setAttribute("list", list);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("curpage", curpage);
	  
	  request.setAttribute("cocktail_jsp", "../cocktail/cocktail_list.jsp");
		request.setAttribute("main_jsp","../cocktail/cocktail_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("cocktail/cocktail_detail.do")
	public String cocktail_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String cno = request.getParameter("cno");
		CocktailVO vo = CocktailDAO.cocktailDetailData(Integer.parseInt(cno));
		List<CocktailVO> list = CocktailDAO.cocktailRecipeData(Integer.parseInt(cno));
		List<CocktailVO> tags = CocktailDAO.cocktailTagData(Integer.parseInt(cno));
		
		request.setAttribute("tags", tags);
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../cocktail/cocktail_detail.jsp");
		return "../main/main.jsp";
	}
}
