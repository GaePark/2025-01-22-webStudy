package com.sist.main;

public class ChangeServlet {
	static String[] pages= {
			"",
			"MusicList",
			"MusicDetail",
			"MusicTypeFind",
			"MusicFind"
	};
	public static String pageChange(int mode)
	{
		String page=pages[mode];
		return page;
	}
}
