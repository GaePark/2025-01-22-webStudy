package com.sist.vo;
import java.util.Date;

import lombok.Data;

/*
 * 
RNO	NUMBER
FNO	NUMBER
ID	VARCHAR2(20 BYTE)
NAME	VARCHAR2(51 BYTE)
MSG	CLOB
REGDATE	DATE
 */

@Data
public class ReplyVO {
	private int rno,mno;
	private String id,name,msg,dbday;
	private Date regdate;
}
