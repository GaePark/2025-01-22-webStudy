package com.sist.vo;

import lombok.Data;

/*
 * 
MNO	NUMBER
CNO	NUMBER
TITLE	VARCHAR2(1000 BYTE)
SINGER	VARCHAR2(500 BYTE)
ALBUM	VARCHAR2(500 BYTE)
POSTER	VARCHAR2(260 BYTE)
IDCREMENT	NUMBER
STATE	VARCHAR2(30 BYTE)
KEY	VARCHAR2(200 BYTE)
HIT	NUMBER
 */
@Data
public class MusicVO {
	private String title,singer,album,poster,state,key;
	private int mno,cno,idcrement,hit;
}
