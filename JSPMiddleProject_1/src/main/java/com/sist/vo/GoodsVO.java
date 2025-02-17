package com.sist.vo;

import lombok.Data;

/*
 * 
 NO							 NUMBER
 GOODS_NAME			 VARCHAR2(1000)
 GOODS_SUB 			 VARCHAR2(1000)
 GOODS_PRICE 		 VARCHAR2(50)
 GOODS_DISCOUNT  NUMBER
 GOODS_FIRST_PRICE  VARCHAR2(20)
 GOODS_DELIVERY 	VARCHAR2(20)
 GOODS_POSTER  		VARCHAR2(260)
 HIT  						NUMBER
 */
// 1. Mybatis => 컬럼명이 동일하지 않는 경우에는 반드시 별칭을 줘야한다
// 2. 컬럼명이 일치하지 않는 경우에는 반드시 설정
/*
 * private int no,gd,hit;
 * SELECT goods_discount as gd
 * 
 * <result property="gd" column="goods_discount">
 * =>함수 이용하면 반드시 별칭
 * 		TO_CHAR() as dbday
 * => 자동으로 값을 채원준다
 * SELECT a,b,c FROM ...
 * setA() setB() setC()
 */
@Data
public class GoodsVO {
	private int no,goods_discount,hit;
	private String goods_name,goods_sub,goods_price,goods_first_price,goods_delivery,goods_poster;
}
