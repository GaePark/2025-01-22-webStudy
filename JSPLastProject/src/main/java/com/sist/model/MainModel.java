package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response)
	{
		FoodVO fvo = FoodDAO.foodMainHouseData();
		List<FoodVO> fList = FoodDAO.foodMainHouseData8();
		ChefVO cvo = RecipeDAO.recipeTodayChef();
		List<RecipeVO> rList = RecipeDAO.recipeData7();

		//chefList => recipeList=> newList=> cookieList=>
		request.setAttribute("fvo", fvo);
		request.setAttribute("fList", fList);
		List<FoodVO> cList = new ArrayList<FoodVO>();

		Cookie[] cookies= request.getCookies();
		if(cookies!=null)
		{
			for(int i=cookies.length-1; i>=0;i--)
			{
				if(cookies[i].getName().startsWith("food_"))
				{
					String fno=cookies[i].getValue();
					FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(fno));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("cvo", cvo);
		request.setAttribute("rList", rList);
		request.setAttribute("cList", cList);
		request.setAttribute("main_jsp", "../main/home.jsp");
		return "../main/main.jsp";
	}
}
