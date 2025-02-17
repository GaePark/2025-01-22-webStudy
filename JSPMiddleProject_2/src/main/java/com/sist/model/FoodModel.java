package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class FoodModel {
	public void FoodListData(HttpServletRequest request)
	{
		
		String page =request.getParameter("page");
		if(page==null) page="1";
		
		int curpage= Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowSize = 12;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = (rowSize*curpage);
		
		map.put("start", start);
		map.put("end", end);
		int fno= 1;
		List<FoodVO> list = FoodDAO.foodListData(map);
		FoodVO vo = FoodDAO.foodDetailData(fno);
		int totalpage = FoodDAO.foodTotalPage();
		final int BLOCK =10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
		{
			endPage=totalpage;
		}
			
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
	}
	public void foodDetailData(HttpServletRequest request)
	{
		String fno=request.getParameter("fno");
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(fno));
		
		request.setAttribute("vo", vo);
	}
}
