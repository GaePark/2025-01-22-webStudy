package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import com.sist.vo.*;
import com.sist.dao.*;
public class InsertOkModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setContent(content);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		
		BoardDAO.boardInsert(vo); 
		return "redirect:list.do";
	}

}
