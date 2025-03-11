package com.sist.model;

import com.sist.controller.Controller; 
import com.sist.controller.RequestMapping;
import com.sist.vo.*;
import com.sist.dao.*;
import java.util.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;







@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response)
	{
		CocktailbarVO cbvo = CocktailbarDAO.cocktailbarHouseData();
		List<CocktailbarVO> chlist = CocktailbarDAO.cocktailbarHouseData12();
		CocktailVO cvo = CocktailDAO.cocktailData();
		int cno=cvo.getCocktail_no();
		List<CocktailVO> crList = CocktailDAO.cocktailRecipeData(cno);
		List<ProductVO> plist = ProductDAO.productHouseData12();
		request.setAttribute("crList", crList);
		request.setAttribute("chlist", chlist);
		request.setAttribute("plist", plist);
		request.setAttribute("cbvo", cbvo);
		request.setAttribute("cvo", cvo);
		request.setAttribute("main_jsp", "../main/home.jsp");
		return "../main/main.jsp";
	}
}
