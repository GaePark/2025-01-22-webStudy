package com.sist.vo;

/*
 FNO  		NUMBER
 NAME 		VARCHAR2(500)
 TYPE  		VARCHAR2(100)
 PHONE  	VARCHAR2(20)
 ADDRESS  	VARCHAR2(700)
 SCORE  	NUMBER(2,1)
 THEME  	CLOB
 POSTER 	VARCHAR2(300)
 IMAGES  	VARCHAR2(4000)
 TIME     	VARCHAR2(100)
 PARKING 	VARCHAR2(200)
 CONTENT  	CLOB
 HIT  		NUMBER
 PRICE   	VARCHAR2(30)
 */
import lombok.Data;

@Data
public class FoodVO {
	private String name,type,phone,address,theme,poster,images,time,parking,content,price;
	private int fno,hit;
	private double score;
}
