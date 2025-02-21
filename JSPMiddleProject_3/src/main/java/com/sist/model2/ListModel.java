package com.sist.model2;

import jakarta.servlet.http.HttpServletRequest;

public class ListModel implements model {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "답변형 게시판");
		return "board/list.jsp";
	}

}
