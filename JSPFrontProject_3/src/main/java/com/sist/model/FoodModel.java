package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class FoodModel {

	@RequestMapping("food/food_find.do")
	public String food_find(HttpServletRequest request, HttpServletResponse response){
		
		
		return "../food/food_find.jsp";
	}
	@RequestMapping("food/find_js.do")
	public void find_js(HttpServletRequest request, HttpServletResponse response)
	{

		//오라클 데이터를 읽어서 => JSON변경 => 자바스크립트 전송
		String page=request.getParameter("page");
		String address = request.getParameter("fd");
		if(address==null) address="마포";
		if(page==null) page="1";
		int curpage = Integer.parseInt(page);
		//List받기
		Map map = new HashMap();
		
		map.put("start", (curpage*12)-11);
		map.put("end",(curpage*12));
		map.put("address", address);
		List<FoodVO> list =FoodDAO.foodFindData(map);
		int totalpage = FoodDAO.foodFindTotalPage(address);
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+10;
		if(endPage>totalpage)
			endPage=totalpage;
		//전송
		JSONArray arr = new JSONArray();
		int i=0;
		for(FoodVO vo:list)
		{
			JSONObject obj = new JSONObject();
			obj.put("fno",vo.getFno());
			obj.put("poster","https://www.menupan.com"+vo.getPoster());
			obj.put("name", vo.getName());
			if(i==0)
			{
				obj.put("curpage",curpage);
				obj.put("totalpage",totalpage);
				obj.put("startPage",startPage);
				obj.put("endPage",endPage);
			}
			arr.add(obj);
			i++;
			
		}
		try {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(arr.toJSONString());

		} catch(Exception ex) {}
	}
	@RequestMapping("food/detail_js.do")
	public void food_detail(HttpServletRequest request, HttpServletResponse response){
		String fno=request.getParameter("fno");
		
		FoodVO vo = FoodDAO.foodFindDetailData(Integer.parseInt(fno));
		JSONObject obj = new JSONObject();
		obj.put("fno", vo.getFno());
		obj.put("name", "https://www.menupan.com"+vo.getPoster());
		obj.put("name", vo.getName());
		obj.put("score", vo.getScore());
		obj.put("address", vo.getAddress());
		obj.put("phone", vo.getPhone());
		obj.put("type", vo.getType());
		obj.put("time", vo.getTime());
		obj.put("parking", vo.getParking());
		obj.put("theme", vo.getTheme());
		obj.put("content", vo.getContent());
		try
		{
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toJSONString());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
