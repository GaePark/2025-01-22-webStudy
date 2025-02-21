package com.sist.vo;
/*
NO	NUMBER
NAME	VARCHAR2(50 BYTE)
SUBJECT	VARCHAR2(2000 BYTE)
CONTENT	CLOB
PWD	VARCHAR2(10 BYTE)
REGDATE	DATE
HIT	NUMBER
 */

import java.util.Date;

import lombok.Data;
@Data
public class BoardVO {
	private int no, hit;
	private String name,subject,content,pwd;
	private Date regdate;
}
	