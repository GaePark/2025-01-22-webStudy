package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
//Controller + MyBatis(DML) => CRUD
public class DetailModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		System.out.println("DETAIL CALL...");
		return null;
	}

}
