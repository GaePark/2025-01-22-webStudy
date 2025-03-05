package com.sist.vo;

import lombok.Data;

/*
FNO	NUMBER
NAME	VARCHAR2(500 BYTE)
TYPE	VARCHAR2(100 BYTE)
PHONE	VARCHAR2(20 BYTE)
ADDRESS	VARCHAR2(700 BYTE)
SCORE	NUMBER(2,1)
THEME	CLOB
POSTER	VARCHAR2(300 BYTE)
IMAGES	VARCHAR2(4000 BYTE)
TIME	VARCHAR2(100 BYTE)
PARKING	VARCHAR2(200 BYTE)
CONTENT	CLOB
HIT	NUMBER
PRICE	VARCHAR2(30 BYTE)
JJIMCOUNT	NUMBER
LIKECOUNT	NUMBER
REPLYCOUNT	NUMBER
 */
@Data
public class FoodVO {
	private int fno,jjimcount,likecount,replycount;
	private double score;
	private String name,type,phone,address,theme,poster,images,time,parking,content,price;
}
