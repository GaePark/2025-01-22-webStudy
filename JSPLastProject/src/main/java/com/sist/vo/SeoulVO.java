package com.sist.vo;
/*
 * NO	NUMBER
TITLE	VARCHAR2(200 BYTE)
POSTER	VARCHAR2(300 BYTE)
MSG	VARCHAR2(4000 BYTE)
ADDRESS	VARCHAR2(300 BYTE)
HIT	NUMBER
LIKECOUNT	NUMBER
REPLYCOUNT	NUMBER
 */
import lombok.Data;

@Data
public class SeoulVO {
	private int no,likecount,replycount,hit;
	private String title,poster,msg,address;
}
