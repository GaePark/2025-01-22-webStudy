package com.sist.main;
import java.util.*;

import com.sist.model.*;
public class MainClass {
	public static void main(String[] args) {
		
		BoardModel bm=new BoardModel();
		Scanner scan = new Scanner(System.in);
		System.out.println("URL 주소 입력:");

		String url=scan.next();
		
		if(url.equals("list.do"))
		{
			bm.boardListData();
		}
		else if(url.equals("Detail.do"))
		{
			bm.boardDteail();
			
		}
		else if(url.equals("insert.do"))
		{
			bm.boardInsert();
			
		}
		else if(url.equals("update.do"))
		{
			bm.boardUpdate();
			
		}
		else if(url.equals("delete.do"))
		{
			bm.boardDelete();;
			
		}
	}
}
