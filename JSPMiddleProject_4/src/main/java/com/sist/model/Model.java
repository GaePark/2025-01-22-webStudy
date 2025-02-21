package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public interface Model {
	//실제 스프링에서 존재
	public String handlerRequest(HttpServletRequest request);
}
