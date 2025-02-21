package com.sist.model2;

import jakarta.servlet.http.HttpServletRequest;

public class UpdateModel implements model {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "게시판 수정");
		return "board/insert.jsp";
	}

}
